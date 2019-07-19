package com.example.kotlin_study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_study.databinding.RecyclerItemBinding
import kotlinx.android.synthetic.main.activity_recycler_view_sample.*

class RecyclerViewSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_sample)

        val adapter = PersonAdapter()
        sample_recyclerView.adapter = adapter

        var people = arrayListOf<Person>()
        for (i in 0..10){
            people.add(Person("사람 $i", age = 20))
        }
        adapter.items = people
        adapter.notifyDataSetChanged()
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
        return PersonViewHolder(RecyclerItemBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.person = items[position]
    }

    class PersonViewHolder(var binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
}
