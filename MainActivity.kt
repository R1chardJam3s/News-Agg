package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.newsaggregator.adapters.TabAdapter
import com.example.newsaggregator.database.SqliteDatabase
import com.example.newsaggregator.notifications.NotificationHelper
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

/**
 * Main activity for News Aggregator app.
 * @author Richard James
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private var notificationHelper : NotificationHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mToolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(mToolbar)

        mAuth = FirebaseAuth.getInstance()

        notificationHelper = NotificationHelper(this)

        val tabs = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabTitles = resources.getStringArray(R.array.tabTitles)
        val mDatabase = SqliteDatabase(this)

        val serviceIntent = Intent(this, APIService::class.java)
        startService(serviceIntent)

        viewPager.adapter = TabAdapter(this)
        TabLayoutMediator(tabs, viewPager, TabLayoutMediator.TabConfigurationStrategy {tab, position ->
            when(position) {
                0 -> tab.text = tabTitles[0]
                1 -> tab.text = tabTitles[1]
                2 -> tab.text = tabTitles[2]
            }
        }).attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_layout, menu)

        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    launchSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Launches the search display after it has been made
     * @param query the keywords being searched
     */
    fun launchSearch(query: String) {
        val intent = Intent(this, DisplayActivity::class.java)
        intent.putExtra("CAT", query)
        startActivity(intent)
    }

    /**
     * Method for calling the select categories screen
     * @param view the button calling the method
     */
    fun launchCat(view: View) {
        val intent = Intent(this, DisplayActivity::class.java)
        val textView = view as TextView
        intent.putExtra("CAT", textView.text)
        startActivity(intent)
    }

    /**
     * Method for calling the account submenu
     * @param item the icon the method is attached to
     */
    fun launchAccount(item: MenuItem) {
        if(mAuth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}