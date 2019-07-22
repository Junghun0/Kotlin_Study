package com.example.kotlin_study.sample04

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class StopWatchViewModel : ViewModel() {
    val _time : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
    private var timerTask: Timer? = null
    private var isRunning = false

    init {
        _time.value = 0
    }

    private fun pause() {
        timerTask?.cancel()
    }

    private fun start() {
        timerTask = timer(period = 10) {
            //background
            //setValue 는 UIThread에서만 사용해야된다. postValue 사용해야된다.
            //_time.value = _time.value?.plus(1)

            _time.postValue(_time.value?.plus(1))//->background에서 사용하는 setvalue
        }
    }

    fun onStartClicked(){
        isRunning = !isRunning
        if (isRunning) {
            start()
        } else {
            pause()
        }
    }

    override fun onCleared() {
        pause()
        timerTask = null
        super.onCleared()
    }
}