package com.example.dailymoodjournaltrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// UI components matching the new XML IDs
private lateinit var buttonShowAll: Button
private lateinit var buttonShowSleepHours: Button
private lateinit var buttonBackToHome: Button
private lateinit var displayAllEmotions: TextView
private lateinit var displayEmotions: TextView

class MoodDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mood_display)
        // Link Kotlin variables to views using updated XML IDs
        buttonShowAll = findViewById(R.id.buttonShowAll)
        buttonShowSleepHours = findViewById(R.id.buttonShowSleepHours)
        buttonBackToHome = findViewById(R.id.buttonBackToHome)
        displayAllEmotions = findViewById(R.id.displayAllEmotions)
        displayEmotions = findViewById(R.id.displayEmotions)

        // Retrieve data passed from ActivityInput using updated key names
        val moodList = intent.getStringArrayListExtra("moodList")
        val typeList = intent.getStringArrayListExtra("typeList")
        val sleepList = intent.getIntegerArrayListExtra("sleepList")
        val journalList = intent.getStringArrayListExtra("journalList")

        // Show all items from the packing list
        buttonShowAll.setOnClickListener {
            if (moodList != null && typeList != null && sleepList != null && journalList != null) {
                val fullList = moodList.indices.joinToString("\n") {
                    "${moodList[it]} (${typeList[it]}) - ${sleepList[it]}: ${journalList[it]}"
                }
                displayEmotions.text = fullList.ifBlank { "No hours slept." }
            } else {
                displayEmotions.text = "No hours slept."
            }
        }

        // Show only items with quantity 2 or more
        buttonShowSleepHours.setOnClickListener {
            if (moodList != null && sleepList != null) {
                val filtered = moodList.indices
                    .filter { sleepList[it] < 6 }
                    .joinToString("\n") {
                        "${moodList[it]} (Quantity: ${sleepList[it]})"
                    }
                displayAllEmotions.text = filtered.ifBlank { "No sleep with hours < 6 added." }
            } else {
                displayAllEmotions.text = "No sleep with hours < 6 added."
            }
        }

        // Return to the input screen
        buttonBackToHome.setOnClickListener {
            finish()
        }
    }
}
