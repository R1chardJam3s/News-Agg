package com.example.newsaggregator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.newsaggregator.adapters.DisplayAdapter
import com.google.firebase.auth.FirebaseAuth

/**
 * Display activity, used to load fragments into this second activity.
 * @author Richard James
 */
class DisplayActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val toolbar = findViewById<Toolbar>(R.id.display_toolbar)
        setSupportActionBar(toolbar)

        val extras = intent.extras

        val cat = extras!!.getString("CAT").toString()
        this.title = cat

        Log.d("Display Type", cat)

        val displayAdapter = DisplayAdapter(this, cat)
        val viewPager = findViewById<ViewPager2>(R.id.display_pager)
        viewPager.adapter = displayAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second_toolbar, menu)
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
}