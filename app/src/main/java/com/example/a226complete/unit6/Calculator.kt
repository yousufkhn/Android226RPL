package com.example.a226complete.unit6

import android.util.Log

class Calculator {
    fun add(a: Int, b: Int): Int {
//        Log.d("Calculator", "Adding $a + $b")
        return a + b
    }

    fun divide(a: Int, b: Int): Int {
        return try {
            a / b
        } catch (e: Exception) {
//            Log.e("Calculator", "Error dividing $a by $b: ${e.message}")
            0 // return default
        }
    }
}