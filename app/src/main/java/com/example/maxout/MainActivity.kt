package com.example.maxout

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var txtWaterIntake: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var sharedPreferences: SharedPreferences
    private var waterIntake = 0
    private var goal = 0  // Default goal is 3L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        createNotificationChannel()

        txtWaterIntake = findViewById(R.id.txtWaterIntake)
        progressBar = findViewById(R.id.progressBar)
        val btnAdd250: Button = findViewById(R.id.btnAdd250)
        val btnAdd500: Button = findViewById(R.id.btnAdd500)
        val btnReset: Button = findViewById(R.id.btnReset)

        sharedPreferences = getSharedPreferences("WaterTracker", MODE_PRIVATE)
        waterIntake = sharedPreferences.getInt("waterIntake", 0)
        goal = sharedPreferences.getInt("goal", 3000)  // Load saved goal

        updateUI()

        btnAdd250.setOnClickListener { addWater(250) }
        btnAdd500.setOnClickListener { addWater(500) }
        btnReset.setOnClickListener { resetWaterIntake() }

        scheduleWaterReminder()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update_goal -> startActivity(Intent(this, UpdateGoalActivity::class.java))
            R.id.menu_bmi_calculator -> startActivity(Intent(this, BMICalculatorActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addWater(amount: Int) {
        waterIntake += amount
        sharedPreferences.edit().putInt("waterIntake", waterIntake).apply()
        updateUI()
    }

    private fun resetWaterIntake() {
        waterIntake = 0
        sharedPreferences.edit().putInt("waterIntake", waterIntake).apply()
        updateUI()
    }

    private fun updateUI() {
        txtWaterIntake.text = "$waterIntake / $goal ml"
        val progress = (waterIntake * 100) / goal
        progressBar.progress = progress
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "WATER_REMINDER_CHANNEL",
                "Water Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun scheduleWaterReminder() {
        val interval = calculateReminderInterval()

        val workRequest = PeriodicWorkRequestBuilder<WaterReminderWorker>(interval, TimeUnit.MINUTES)
            .setInitialDelay(10, TimeUnit.SECONDS) // First notification in 10 sec for testing
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "WATER_REMINDER",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun calculateReminderInterval(): Long {
        val remainingWater = goal - waterIntake
        val intervalsLeft = if (remainingWater > 0) remainingWater / 250 else 1
        return (12 * 60).toLong()  / intervalsLeft // Spread reminders over 12 hours
    }
}
