package com.example.kotlin_study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

//상속은 뒤에 생성자가 붙는다() , AnkoLogger는 interface 이므로 아무것도 안붙는다.
class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener { Log.e("Onclick", "Test!") }

        //자바 화면전환
        //startActivity(Intent(this, Sample1Activity::class.java))

        //anko를 사용한 화면전환
        startActivity<Sample1Activity>("id" to 1, "name" to "Junghoon")
        //anko를 이용한 Toast 메시지
        toast("Click Toast")

        var item = ItemKotlin("Test", 1)

        //배열
        val numbers = arrayOf(1, 2, 3, 4, 5)
        //리스트
        val numberList = arrayListOf(1, 2, 3, 4, 5)
        val hashMap = hashMapOf(
            "Key" to "Value"
        )

        //코틀린에는 삼항연산자가 없음
        val a = 10
        val b = 20
        val max = if (a > b) a else b

        val x = 1
        when (x) {
            1 -> debug(x)
            in 3..5 -> debug("3~5")
        }

        val numState = when (x % 2) {
            0 -> "짝"
            else -> "홀"
        }
    }

    //public void isEven(int number){}
    fun isEven(number: Int): String {
        return when (number % 2) {
            0 -> "짝"
            else -> "홀"
        }
    }

    fun isEven2(number: Int): String = when (number % 2) {
            0 -> "짝"
            else -> "홀"
        }
}
