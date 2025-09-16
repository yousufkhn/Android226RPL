package com.example.a226complete.unit3.bound

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log


class MyBoundService: Service() {
    private val TAG = "MyBoundService"

    private val binder = LocalBinder()

    inner class LocalBinder : Binder(){
        fun getService() : MyBoundService = this@MyBoundService
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG,"Service bound")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG,"Service unbound")
        return super.onUnbind(intent)
    }

    fun getRandomNumber():Int{
        return (1..100).random()
    }
}