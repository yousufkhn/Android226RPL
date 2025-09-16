package com.example.a226complete.unit4.room

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a226complete.R
import kotlin.jvm.java

class RoomActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val tvUsers = findViewById<TextView>(R.id.tvUsers)

        // 🔹 Get DAO + ViewModel
        val userDao = AppDatabase.getDatabase(application).userDao()
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userDao))[UserViewModel::class.java]

        // 🔹 Add new user
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                userViewModel.insertUser(User(name = name, email = email, phone = phone))
                etName.text.clear()
                etEmail.text.clear()
                etPhone.text.clear()
            }
        }

        // 🔹 Observe and display users as plain text
        userViewModel.allUsers.observe(this) { users ->
            val text = users.joinToString("\n") { user ->
                "${user.id}: ${user.name} (${user.email}) - ${user.phone ?: "N/A"}"
            }
            tvUsers.text = text.ifEmpty { "No users yet" }
        }
    }
}
