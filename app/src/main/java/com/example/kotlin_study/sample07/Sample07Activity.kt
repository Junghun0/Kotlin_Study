package com.example.kotlin_study.sample07

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_study.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/*
{
  "userId": 1,
  "id": 1,
  "title": "delectus aut autem",
  "completed": false
 }
*/
data class Response(val userId: Int, val id: Int, val title: String, val completed: Boolean)

interface JsonPlaceHolderApi{
    @GET("/todos/{id}")
    fun getTodo(@Path("id") id: Int): Deferred<Response>

    @GET("/todos/")
    fun getTodos(): Deferred<List<Response>>
}

class Sample07Activity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample07)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(JsonPlaceHolderApi::class.java)

        //비동기 백그라운드
        launch {
            //순차적으로 실행됨
            val response = service.getTodo(1).await()
            val response2 = service.getTodos().await()

            //UI 갱신
            launch(Dispatchers.Main){
                Log.e("response",""+response)

                delay(2000)

                Log.e("response2",""+response2)
            }
        }
    }
}
