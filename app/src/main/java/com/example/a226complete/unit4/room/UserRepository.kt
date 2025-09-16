package com.example.a226complete.unit4.room

class UserRepository(private val userDao: UserDao) {
    val allUsers = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
}