package com.example.maxout

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateGoalActivity : AppCompatActivity() {
    private lateinit var edtGoal: EditText
    private lateinit var btnSave: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_goal)

        edtGoal = findViewById(R.id.edtGoal)
        btnSave = findViewById(R.id.btnSave)
        sharedPreferences = getSharedPreferences("WaterTracker", MODE_PRIVATE)

        // Load current goal in EditText
        val currentGoal = sharedPreferences.getInt("goal", 3000)
        edtGoal.setText(currentGoal.toString())

        btnSave.setOnClickListener {
            val newGoal = edtGoal.text.toString().toIntOrNull()

            if (newGoal != null && newGoal > 0) {
                sharedPreferences.edit().putInt("goal", newGoal).apply()

                Toast.makeText(this, "Goal updated!", Toast.LENGTH_SHORT).show()

                // Send result back to MainActivity
                setResult(RESULT_OK, Intent())
                finish() // Close activity and return to MainActivity
            } else {
                Toast.makeText(this, "Enter a valid goal!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
