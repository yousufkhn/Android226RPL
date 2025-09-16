package com.example.a226complete.unit4.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a226complete.R

class SQLiteHome : AppCompatActivity() {

    private lateinit var dbHelper: MyDatabaseHelper
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sqlite_home)


        dbHelper = MyDatabaseHelper(this)

        val etName: EditText = findViewById(R.id.etName)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etUpdateId: EditText = findViewById(R.id.etUpdateId)
        val etUpdateEmail: EditText = findViewById(R.id.etUpdateEmail)
        val etDeleteId: EditText = findViewById(R.id.etDeleteId)

        val btnInsert: Button = findViewById(R.id.btnInsert)
        val btnRead: Button = findViewById(R.id.btnRead)
        val btnUpdate: Button = findViewById(R.id.btnUpdate)
        val btnDelete: Button = findViewById(R.id.btnDelete)

        tvResult = findViewById(R.id.tvResult)

        // Insert
        btnInsert.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val result = dbHelper.insertUser(name, email)
            if (result != -1L) {
                tvResult.text = "Inserted: $name ($email)"
            } else {
                tvResult.text = "Insert failed!"
            }
        }

        // Read
        btnRead.setOnClickListener {
            val users = dbHelper.getAllUsers()
            tvResult.text = if (users.isNotEmpty()) {
                users.joinToString("\n")
            } else {
                "No users found!"
            }
        }

        // Update
        btnUpdate.setOnClickListener {
            val id = etUpdateId.text.toString().toIntOrNull()
            val newEmail = etUpdateEmail.text.toString()
            if (id != null) {
                val rows = dbHelper.updateUser(id, newEmail)
                tvResult.text = if (rows > 0) "Updated User ID $id" else "User not found!"
            } else {
                tvResult.text = "Enter valid ID"
            }
        }

        // Delete
        btnDelete.setOnClickListener {
            val id = etDeleteId.text.toString().toIntOrNull()
            if (id != null) {
                val rows = dbHelper.deleteUser(id)
                tvResult.text = if (rows > 0) "Deleted User ID $id" else "User not found!"
            } else {
                tvResult.text = "Enter valid ID"
            }
        }
    }
}