package com.example.a226complete.unit4.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) :
SQLiteOpenHelper(context,"MyDB",null,1){
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(
            "CREATE TABLE users("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "name TEXT,"+
                    "email TEXT)"
        )
    }

    override fun onUpgrade(db:SQLiteDatabase,oldVersion:Int,newVersion:Int){
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    // Insert Data
    fun insertUser(name:String,email:String):Long{
        val db = writableDatabase
        val values = ContentValues().apply{
            put("name",name)
            put("email",email)
        }
        return db.insert("users",null,values)
    }

    // Update user
    fun updateUser(id: Int, newEmail: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("email", newEmail)
        }
        return db.update("users", values, "id = ?", arrayOf(id.toString()))
    }

    // Read users
    fun getAllUsers():List<String>{
        val db = readableDatabase
        val cursor = db.query("users",arrayOf("id","name","email"),null,null,null,null,null)

        val users = mutableListOf<String>()
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            users.add("$id - $name - $email")
        }
        cursor.close()
        return users
    }

    // Delete users
    fun deleteUser(id:Int):Int{
        val db = writableDatabase
        return db.delete("users","id = ?",arrayOf(id.toString()))
    }
}
