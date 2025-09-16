package com.example.a226complete.unit3.bound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a226complete.R

class BoundActivity : AppCompatActivity() {

    private var myService: MyBoundService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyBoundService.LocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            myService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bound)

        val btnBind = findViewById<Button>(R.id.btnBind)
        val btnUnbind = findViewById<Button>(R.id.btnUnbind)
        val btnGetData = findViewById<Button>(R.id.btnGetData)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        val intent = Intent(this, MyBoundService::class.java)


        btnBind.setOnClickListener {
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }


        btnUnbind.setOnClickListener {
            if (isBound) {
                unbindService(connection)
                isBound = false
            }
        }

        btnGetData.setOnClickListener {
            if (isBound) {
                val number = myService?.getRandomNumber()
                txtResult.text = "Random Number: $number"
            } else {
                txtResult.text = "Service not bound"
            }
        }
    }
}