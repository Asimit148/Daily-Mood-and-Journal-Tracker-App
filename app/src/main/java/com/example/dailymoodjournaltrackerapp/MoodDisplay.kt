package com.example.dailymoodjournaltrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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

        // Link UI components to their XML counterparts
        buttonShowAll = findViewById(R.id.buttonShowAll)
        buttonShowSleepHours = findViewById(R.id.buttonShowSleepHours)
        buttonBackToHome = findViewById(R.id.buttonBackToHome)
        displayAllEmotions = findViewById(R.id.displayAllEmotions)
        displayEmotions = findViewById(R.id.displayEmotions)

        // Retrieve data passed from MoodInput
        val moodList = intent.getStringArrayListExtra("moodList")
        val typeList = intent.getStringArrayListExtra("typeList")
        val sleepList = intent.getIntegerArrayListExtra("sleepList")
        val journalList = intent.getStringArrayListExtra("journalList")

        // Show all mood records
        buttonShowAll.setOnClickListener {
            if (moodList != null && typeList != null && sleepList != null && journalList != null) {
                val fullList = moodList.indices.joinToString("\n") {
                    "Mood: ${moodList[it]} (${typeList[it]})\nSleep: ${sleepList[it]} hours\nJournal: ${journalList[it]}\n"
                }
                displayEmotions.text = fullList.ifBlank { "No mood entries to display." }
            } else {
                displayEmotions.text = "No mood entries to display."
            }
        }

        // Filter and show moods with less than 6 hours of sleep
        buttonShowSleepHours.setOnClickListener {
            if (moodList != null && sleepList != null && typeList != null) {
                val filtered = moodList.indices
                    .filter { sleepList[it] < 6 }
                    .joinToString("\n") {
                        "Mood: ${moodList[it]} (${typeList[it]}) - Sleep: ${sleepList[it]} hours"
                    }
                displayAllEmotions.text = filtered.ifBlank { "No entries with sleep < 6 hours found." }
            } else {
                displayAllEmotions.text = "No entries with sleep < 6 hours found."
            }
        }

        // Go back to input screen
        buttonBackToHome.setOnClickListener {
            finish()
        }
    }
}
