package com.example.a226complete.ca1

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a226complete.R

class CA1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ca1)

        val gridView: GridView = findViewById(R.id.gridView)
        val contacts = listOf(
            Contact("Ram", 123456789),
            Contact("Bob", 987654321),
            Contact("Yousuf", 112233445)
        )

        val adapter = GridAdapter(this, contacts)
        gridView.adapter = adapter

        gridView.setOnItemClickListener{_,_,position,_ ->
            val contact = contacts[position]
            Toast.makeText(this, "Calling: ${contact.name}", Toast.LENGTH_SHORT).show()
        }

    }
}