package com.example.dailymoodjournaltrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Get reference started button by it's id from the xml layout!
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val buttonExit = findViewById<Button>(R.id.buttonExit)

        buttonNext.setOnClickListener {
            // create an intent to navigate to the PackageListActivity when the button is clicked.
            startActivity(Intent(this, MoodInput::class.java))
        }

        buttonExit.setOnClickListener {
            // This will allow you to exit the app when clicked.
            finishAffinity()
        }
    }
}
