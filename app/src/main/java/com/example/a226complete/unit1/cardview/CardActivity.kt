package com.example.a226complete.unit1.cardview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a226complete.R

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val fruits = listOf("Apple", "Banana", "Mango", "Orange", "Pineapple", "Grapes")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CardAdapter(fruits) { fruit ->
            Toast.makeText(this, "Clicked: $fruit", Toast.LENGTH_SHORT).show()
        }
    }
}