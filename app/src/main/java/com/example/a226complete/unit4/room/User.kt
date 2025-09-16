package com.example.a226complete.unit4.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id:Int = 0, // this will autoincrement primary key
    val name: String,
    val email:String,
    val phone:String? = null // optional column nullable
)