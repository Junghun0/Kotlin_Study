package com.example.kotlin_study.sample09

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.kotlin_study.R


class Sample09Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample09)

        lifecycle.addObserver(MyObserver())
    }

    public override fun onStart() {
        super.onStart()
    }

    public override fun onStop() {
        super.onStop()
    }
}

class MyObserver : LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun callOnResume(){
        Log.d("lifecycle observer->","onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun callOnStop(){
        Log.d("lifecycle observer->","onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun callOnStart(){
        Log.d("lifecycle observer->","onStart")
    }

}
