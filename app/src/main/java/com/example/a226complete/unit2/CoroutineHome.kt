package com.example.a226complete.unit2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import com.example.a226complete.R

class CoroutineHome : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutine_home)

        val txtView = findViewById<TextView>(R.id.txt_view)
        val startCoroutineBtn = findViewById<Button>(R.id.startCoroutineBtn)
        val startWithTimeoutBtn = findViewById<Button>(R.id.startWithTimeoutBtn)
        val startCancellableBtn = findViewById<Button>(R.id.startCancellableBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        val collectFlowBtn = findViewById<Button>(R.id.collectFlowBtn)
        val collectTwiceBtn = findViewById<Button>(R.id.collectTwiceBtn)

        // Observe LiveData
        viewModel.data.observe(this, Observer { resultText ->
            txtView.text = resultText
        })

        // Button listeners
        startCoroutineBtn.setOnClickListener { viewModel.startWork() }
        startWithTimeoutBtn.setOnClickListener { viewModel.startWithTimeout() }
        startCancellableBtn.setOnClickListener { viewModel.startCancellableWork() }
        cancelBtn.setOnClickListener { viewModel.cancelWork() }
        collectFlowBtn.setOnClickListener { viewModel.collectFlow() }
        collectTwiceBtn.setOnClickListener { viewModel.collectTwice() }
    }
}
