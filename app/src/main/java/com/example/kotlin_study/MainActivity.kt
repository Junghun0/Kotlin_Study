package com.example.kotlin_study

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_study.databinding.ItemSubjectBinding
import com.example.kotlin_study.sample01.RecyclerViewSampleActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger

//상속은 뒤에 생성자가 붙는다() , AnkoLogger는 interface 이므로 아무것도 안붙는다.
class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SubjectAdapter{subject ->
            val intent = Intent(this, subject.clazz)
            startActivity(intent)
        }
        main_recyclerView.adapter = adapter

        val subjects = arrayListOf<Subject>()
        subjects.add(Subject("RecyclerView Sample!", RecyclerViewSampleActivity::class.java))

        adapter.items = subjects
        adapter.notifyDataSetChanged()
    }
}

data class Subject(var title: String, val clazz: Class<RecyclerViewSampleActivity>)

class SubjectAdapter(private val clickListener: (person: Subject) -> Unit):
        RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>(){
    var items = arrayListOf<Subject>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)
        val viewHolder = SubjectViewHolder(ItemSubjectBinding.bind(view))
        view.setOnClickListener{
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.subject = items[position]
    }

    class SubjectViewHolder(val binding: ItemSubjectBinding): RecyclerView.ViewHolder(binding.root)

}

