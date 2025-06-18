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
private lateinit var buttonShowFiltered: Button
private lateinit var buttonBackToHome: Button
private lateinit var displayAllItems: TextView
private lateinit var displayFilteredItems: TextView

class MoodDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mood_display)
        // Link Kotlin variables to views using updated XML IDs
        buttonShowAll = findViewById(R.id.buttonShowAll)
        buttonShowFiltered = findViewById(R.id.buttonShowFiltered)
        buttonBackToHome = findViewById(R.id.buttonBackToHome)
        displayAllItems = findViewById(R.id.displayAllItems)
        displayFilteredItems = findViewById(R.id.displayFilteredItems)

        // Retrieve data passed from ActivityInput using updated key names
        val dayList = intent.getStringArrayListExtra("dayList")
        val categoryList = intent.getStringArrayListExtra("categoryList")
        val quantityList = intent.getIntegerArrayListExtra("quantityList")
        val commentList = intent.getStringArrayListExtra("commentList")

        // Show all items from the packing list
        buttonShowAll.setOnClickListener {
            if (dayList != null && categoryList != null && quantityList != null && commentList != null) {
                val fullList = dayList.indices.joinToString("\n") {
                    "${dayList[it]} (${categoryList[it]}) - ${quantityList[it]}: ${commentList[it]}"
                }
                displayAllItems.text = fullList.ifBlank { "No items added." }
            } else {
                displayAllItems.text = "No items added."
            }
        }

        // Show only items with quantity 2 or more
        buttonShowFiltered.setOnClickListener {
            if (dayList != null && quantityList != null) {
                val filtered = dayList.indices
                    .filter { quantityList[it] >= 30 }
                    .joinToString("\n") {
                        "${dayList[it]} (Quantity: ${quantityList[it]})"
                    }
                displayFilteredItems.text = filtered.ifBlank { "No items with quantity ≥ 30 added." }
            } else {
                displayFilteredItems.text = "No items with quantity ≥ 30 added."
            }
        }

        // Return to the input screen
        buttonBackToHome.setOnClickListener {
            finish()
        }
    }
}
