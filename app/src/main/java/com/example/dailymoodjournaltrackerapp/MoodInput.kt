package com.example.dailymoodjournaltrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MoodInput : AppCompatActivity() {

    private val moodList = arrayListOf<String>()
    private val typeList = arrayListOf<String>()
    private val sleepList = arrayListOf<Int>()
    private val journalList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mood_input)

        val inputMood = findViewById<EditText>(R.id.inputMood)
        val inputMoodType = findViewById<EditText>(R.id.inputMoodType)
        val inputSleepHours = findViewById<EditText>(R.id.inputSleepHours)
        val inputJournalEntry = findViewById<EditText>(R.id.inputJournalEntry)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonView = findViewById<Button>(R.id.buttonView)
        val buttonExit = findViewById<Button>(R.id.buttonExit)
        val buttonClear = findViewById<Button>(R.id.buttonClear)

        buttonAdd.setOnClickListener {
            val mood = inputMood.text.toString().trim()
            val type = inputMoodType.text.toString().trim()
            val sleepHoursStr = inputSleepHours.text.toString().trim()
            val journalEntry = inputJournalEntry.text.toString().trim()

            if (mood.isBlank() || type.isBlank() || sleepHoursStr.isBlank()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sleep = sleepHoursStr.toIntOrNull()
            if (sleep == null || sleep <= 0) {
                Toast.makeText(this, "Sleep hours must be a positive number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            moodList.add(mood)
            typeList.add(type)
            sleepList.add(sleep)
            journalList.add(journalEntry)

            Toast.makeText(this, "Entry added to the list", Toast.LENGTH_SHORT).show()

            inputMood.text.clear()
            inputMoodType.text.clear()
            inputSleepHours.text.clear()
            inputJournalEntry.text.clear()
        }

        buttonView.setOnClickListener {
            val intent = Intent(this, MoodDisplay::class.java)
            intent.putStringArrayListExtra("moodList", ArrayList(moodList))
            intent.putStringArrayListExtra("typeList", ArrayList(typeList))
            intent.putIntegerArrayListExtra("sleepList", ArrayList(sleepList))
            intent.putStringArrayListExtra("journalList", ArrayList(journalList))
            startActivity(intent)
        }

        buttonClear.setOnClickListener {
            inputMood.text.clear()
            inputMoodType.text.clear()
            inputSleepHours.text.clear()
            inputJournalEntry.text.clear()
            Toast.makeText(this, "Inputs cleared!", Toast.LENGTH_SHORT).show()
        }

        buttonExit.setOnClickListener {
            finishAffinity()
        }
    }
}
