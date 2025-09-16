package com.example.a226complete.unit3.fab

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a226complete.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fab_home)


        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            Toast.makeText(this, "FAB Clicked!", Toast.LENGTH_SHORT).show()
        }

        // control progamatically
//        fab.hide()
//        fab.show()
    }
}