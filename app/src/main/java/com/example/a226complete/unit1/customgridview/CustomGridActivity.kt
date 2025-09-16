package com.example.a226complete.unit1.customgridview

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a226complete.R

class CustomGridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_grid)
        val gridView = findViewById<GridView>(R.id.gridView)

        val titles = listOf("Home", "Profile", "Settings", "Messages", "Gallery", "Logout")
        val images = listOf(
            R.drawable.ic_launcher_background,    // add your icons in drawable
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )

        val adapter = GridAdapter(this, titles, images)
        gridView.adapter = adapter

        // click event
        gridView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Clicked: ${titles[position]}", Toast.LENGTH_SHORT).show()
        }
    }
}