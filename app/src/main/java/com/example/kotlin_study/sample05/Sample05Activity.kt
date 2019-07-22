package com.example.kotlin_study.sample05

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_study.R
import kotlinx.android.synthetic.main.activity_sample05.*
import java.util.*

class Sample05Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample05)

        add_event_button.setOnClickListener {
            val beginTime = Calendar.getInstance()
            beginTime.set(2019,7,22,7, 30)
            val endTime = Calendar.getInstance()
            endTime.set(2019,7,22,12,30)
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                .putExtra(CalendarContract.Events.TITLE,"MyEvent")
                .putExtra(CalendarContract.Events.DESCRIPTION,"Group Class")

            startActivity(intent)
        }

        add_event_button2.setOnClickListener {

        }
    }
}
