package com.example.a226complete.unit2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


class MyViewModel: ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    // ------------------------
    // 1. Suspending Function + Context Switching
    // ------------------------
    private suspend fun fetchData():String = withContext(Dispatchers.IO){
        delay(1000)
        return@withContext "Hello From Suspend Function!"
    }

    fun startWork(){
        viewModelScope.launch{
            try{
                _data.value = "Starting..."
                val data = fetchData()
                _data.value = "Fetched Data -> ${data}"
            }catch(e:Exception){
                _data.value = "Error -> ${e.message}"
            }
        }
    }

    // ------------------------
    // 2. Timeout + Exception Handling
    // ------------------------
    fun startWithTimeout(){
        viewModelScope.launch{
            try{
                val data = withTimeout(1500){
                    delay(2000)
                    "Completed before Timeout"
                }
                _data.value = data
            }catch (e: TimeoutCancellationException){
                _data.value = "Timed Out"
            }
        }
    }

    // ------------------------
    // 3. Manual Cancellation with Job
    // ------------------------
    private var job: Job? = null

    fun startCancellableWork() {
        job = viewModelScope.launch {
            repeat(8) { i ->
                delay(700)
                _data.value = "Working... step $i"
            }
            _data.value = "Work Completed"
        }
    }

    fun cancelWork() {
        job?.cancel()
        _data.value = "Work Cancelled!"
    }

    // ------------------------
    // 4. Flow + Operators + Cold Stream
    // ------------------------
    private fun createFlow(): Flow<Int> = flow {
        emit(1)
        delay(500)
        emit(2)
        delay(500)
        emit(3)
    }

    fun collectFlow() {
        viewModelScope.launch {
            createFlow()
                .map { it * 2 }             // operator: transform values
                .filter { it > 2 }          // operator: filter values
                .onEach { _data.value = "Flow emitted $it" }
                .catch { e -> _data.value = "Error in flow -> ${e.message}" } // exception handling
                .collect()
        }
    }

    // Demonstrating Cold Stream (flow restarts for each collector)
    fun collectTwice() {
        viewModelScope.launch {
            createFlow().collect { _data.value = "Collector1 -> $it" }
            createFlow().collect { _data.value = "Collector2 -> $it" }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}