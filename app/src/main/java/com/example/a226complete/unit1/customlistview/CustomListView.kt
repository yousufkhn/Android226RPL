package com.example.a226complete.unit1.customlistview

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a226complete.R

class CustomListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_list_view)

        val listView = findViewById<ListView>(R.id.listView)

        val users = listOf(
            User("Yousuf Khan", "yousuf@mail.com"),
            User("Ali Ahmed", "ali@mail.com"),
            User("Sara Khan", "sara@mail.com")
        )

        val adapter = CustomListAdapter(this, users)
        listView.adapter = adapter

        // click event
        listView.setOnItemClickListener { _, _, position, _ ->
            val user = users[position]
            Toast.makeText(this, "Clicked: ${user.name}", Toast.LENGTH_SHORT).show()
        }
    }
}