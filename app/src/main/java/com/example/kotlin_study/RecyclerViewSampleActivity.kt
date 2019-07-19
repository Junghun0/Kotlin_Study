package com.example.kotlin_study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_study.databinding.RecyclerItemBinding

class RecyclerViewSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_sample)
    }
}

//model
data class Person(var name: String, var age: Int)

//Adapter
class PersonAdapter: RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){
    var items = arrayListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item,parent,false)
        return PersonViewHolder(RecyclerItemBinding.bind(view).root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
