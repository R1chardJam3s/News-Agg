package com.example.newsaggregator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Activity to handle login screen
 */
class LoginActivity() : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = findViewById<Toolbar>(R.id.login_toolbar)
        setSupportActionBar(toolbar)

        val intent = Intent(this, AccountActivity::class.java)

        mAuth = FirebaseAuth.getInstance()

        val emailEntry = findViewById<EditText>(R.id.emailEntry)
        val passwordEntry = findViewById<EditText>(R.id.passwordEntry)

        val loginButton = findViewById<Button>(R.id.logInButton)
        loginButton.setOnClickListener{ view ->
            mAuth.signInWithEmailAndPassword(
                emailEntry.text.toString(),
                passwordEntry.text.toString()
            )
                .addOnCompleteListener(
                    this
                ) { task ->
                    if(task.isSuccessful) {
                        val user = mAuth.currentUser
                        startActivity(intent)
                    }
                }
        }
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener{ view ->
            mAuth.createUserWithEmailAndPassword(
                emailEntry.text.toString(),
                passwordEntry.text.toString()
            )
                .addOnCompleteListener(
                    this
                ) { task ->
                    if(task.isSuccessful) {
                        val user = mAuth.currentUser
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Authentication Failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
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