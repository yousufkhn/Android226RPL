package com.example.a226complete.unit3.unbound

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a226complete.R

class UnBoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_un_bound)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)

        val serviceIntent = Intent(this, MyUnboundService::class.java)

        btnStart.setOnClickListener {
            startService(serviceIntent)
        }

        btnStop.setOnClickListener {
            stopService(serviceIntent)
        }
    }
}