package com.example.a226complete.unit6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a226complete.R

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val result = findViewById<TextView>(R.id.result)

        addBtn.setOnClickListener {
            val a = num1.text.toString().toIntOrNull() ?: 0
            val b = num2.text.toString().toIntOrNull() ?: 0
            result.text = (a + b).toString()
        }
    }
}