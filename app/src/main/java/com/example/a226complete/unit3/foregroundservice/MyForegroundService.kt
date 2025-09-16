package com.example.a226complete.unit3.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.a226complete.R

class MyForegroundService: Service() {

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()

        // Create notification channel (for Android O+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "My Foreground Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service Running")
            .setContentText("Doing some work in the background…")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true) // can't be swiped away
            .build()

        // Start service in foreground with notification
        startForeground(1, notification)

        // Here you can start long-running work in background thread if needed

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // we don’t bind here
    }

    override fun onDestroy() {
        super.onDestroy()
        // cleanup work if needed
    }

}