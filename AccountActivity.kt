package com.example.newsaggregator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth

/**
 * Activity for the account screen
 * @author Richard James
 */
class AccountActivity() : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val mAuth = FirebaseAuth.getInstance()

        val toolbar = findViewById<Toolbar>(R.id.account_toolbar)
        setSupportActionBar(toolbar)

        val nameLabel = findViewById<TextView>(R.id.nameLabel)
        val emailLabel = findViewById<TextView>(R.id.usernameLabel)
        nameLabel.text = mAuth.currentUser!!.email
        emailLabel.text = mAuth.currentUser!!.email

        val logOutButton =  findViewById<Button>(R.id.logOutButton)
        logOutButton.setOnClickListener{view ->
            mAuth.signOut()
        }
        mAuth.addAuthStateListener {
            if(mAuth.currentUser == null) {
                this.finish()
            }
        }
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

    /**
     * Method for loading the history fragment into the Display
     * @param view button the method is called from
     */
    fun loadHistory(view : View) {
        val intent = Intent(this, DisplayActivity::class.java)
        val button = findViewById<Button>(R.id.historyButton)
        intent.putExtra("CAT", button.text)
        startActivity(intent)
    }

    /**
     * Method for loading the preferences fragment into the Display
     * @param view button the method is called from
     */
    fun selectPref(view : View) {
        val intent = Intent(this, DisplayActivity::class.java)
        val button = findViewById<Button>(R.id.selcCatButton)
        intent.putExtra("CAT", button.text)
        startActivity(intent)
    }

    /**
     * Method for loading the saved fragment into the Display
     * @param view button the method is called from
     */
    fun loadSaved(view : View) {
        val intent = Intent(this, DisplayActivity::class.java)
        val button = findViewById<Button>(R.id.savedButton)
        intent.putExtra("CAT", button.text)
        startActivity(intent)
    }
}