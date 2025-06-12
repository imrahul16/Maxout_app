package com.example.maxout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BMICalculatorActivity : AppCompatActivity() {
    private lateinit var edtHeight: EditText
    private lateinit var edtWeight: EditText
    private lateinit var txtBMIResult: TextView
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        edtHeight = findViewById(R.id.edtHeight)
        edtWeight = findViewById(R.id.edtWeight)
        txtBMIResult = findViewById(R.id.txtBMIResult)
        btnCalculate = findViewById(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val heightText = edtHeight.text.toString()
            val weightText = edtWeight.text.toString()

            // ✅ Input validation to prevent app crash
            if (heightText.isEmpty() || weightText.isEmpty()) {
                Toast.makeText(this, "Please enter both height and weight!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val height = heightText.toDoubleOrNull()
            val weight = weightText.toDoubleOrNull()

            if (height == null || weight == null || height <= 0 || weight <= 0) {
                Toast.makeText(this, "Enter valid positive numbers!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val heightInMeters = height / 100
            val bmi = weight / (heightInMeters * heightInMeters)

            val status = when {
                bmi < 18.5 -> "Underweight"
                bmi < 24.9 -> "Normal weight"
                bmi < 29.9 -> "Overweight"
                else -> "Obese"
            }

            txtBMIResult.text = "BMI: %.2f\nStatus: %s".format(bmi, status)
        }
    }
}
