package com.example.habittracker

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var habitSpinner: Spinner
    private lateinit var addHabitButton: Button
    private lateinit var habitRecyclerView: RecyclerView
    private val habitList = mutableListOf<String>()
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        habitSpinner = findViewById(R.id.habit_spinner)
        addHabitButton = findViewById(R.id.add_habit_button)
        habitRecyclerView = findViewById(R.id.habit_recycler_view)

        // Set up the Spinner with a list of habits
        val habits = arrayOf("Exercise", "Meditation", "Reading", "Sleeping Early", "Healthy Eating")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, habits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        habitSpinner.adapter = adapter

        // Set up the Spinner item selected listener
        habitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedHabit = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected: $selectedHabit", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set up the RecyclerView
        habitAdapter = HabitAdapter(habitList)
        habitRecyclerView.adapter = habitAdapter
        habitRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the Button click listener
        addHabitButton.setOnClickListener {
            val selectedHabit = habitSpinner.selectedItem.toString()
            habitList.add(selectedHabit)
            habitAdapter.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, "Habit Added: $selectedHabit", Toast.LENGTH_SHORT).show()
        }
    }
}
