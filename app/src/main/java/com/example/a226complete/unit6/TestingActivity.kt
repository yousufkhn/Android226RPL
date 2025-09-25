package com.example.a226complete.unit6

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a226complete.R

class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_testing)

        val calc = Calculator()

        val sum = calc.add(5, 3)
        Log.d("MainActivity", "Sum = $sum")

        val div = calc.divide(10, 0) // will log error
        Log.d("MainActivity", "Division result = $div")
    }
}