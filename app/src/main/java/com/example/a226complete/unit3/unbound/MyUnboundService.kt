package com.example.a226complete.unit3.unbound

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyUnboundService: Service() {
    private val TAG = "MyUnboundService"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"Unbound service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"Service started")

        Thread{
            for(i in 1..5){
                Thread.sleep(1000)
                Log.d(TAG,"wordking in $i")
            }
            stopSelf()
        }.start()

    return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Service destroyed")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}