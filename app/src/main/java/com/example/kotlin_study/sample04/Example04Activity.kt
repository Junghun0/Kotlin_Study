package com.example.kotlin_study.sample04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin_study.R
import kotlinx.android.synthetic.main.activity_example04.*

class Example04Activity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example04)

        val viewModel = ViewModelProviders.of(this).get(StopWatchViewModel::class.java)

        viewModel._time.observe(this, Observer { _time ->
            val sec = _time / 100
            val milli = _time % 100

            //UI 갱신
            second_textView.text = "$sec"
            mill_second_textView.text = "$milli"
        });

        start_button.setOnClickListener {
            viewModel.onStartClicked()
        }
    }
}

