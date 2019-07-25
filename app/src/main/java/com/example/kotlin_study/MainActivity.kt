package com.example.kotlin_study

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_study.databinding.ItemSubjectBinding
import com.example.kotlin_study.sample01.RecyclerViewSampleActivity
import com.example.kotlin_study.sample02.Sample02Activity
import com.example.kotlin_study.sample03.Sample03Activity
import com.example.kotlin_study.sample04.Example04Activity
import com.example.kotlin_study.sample05.Sample05Activity
import com.example.kotlin_study.sample06.Sample06Activity
import com.example.kotlin_study.sample07.Sample07Activity
import com.example.kotlin_study.sample09.Sample09Activity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger

//상속은 뒤에 생성자가 붙는다() , AnkoLogger는 interface 이므로 아무것도 안붙는다.
class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SubjectAdapter { subject ->
            val intent = Intent(this, subject.clazz)
            startActivity(intent)
        }
        main_recyclerView.adapter = adapter

        val subjects = arrayListOf<Subject>()
        subjects.add(Subject("RecyclerView Sample!", RecyclerViewSampleActivity::class.java))
        subjects.add(Subject("Sample02_fragment", Sample02Activity::class.java))
        subjects.add(Subject("BMI calculator!", Sample03Activity::class.java))
        subjects.add(Subject("StopWatch!",Example04Activity::class.java))
        subjects.add(Subject("Google Calendar!",Sample05Activity::class.java))
        subjects.add(Subject("Gallery", Sample06Activity::class.java))
        subjects.add(Subject("Retrofit + Coroutine", Sample07Activity::class.java))
        subjects.add(Subject("LifeCycle Sample!", Sample09Activity::class.java))

        adapter.items = subjects
        adapter.notifyDataSetChanged()

        main_recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }
}

data class Subject(var title: String, val clazz: Class<out Activity>)

class SubjectAdapter(private val clickListener: (person: Subject) -> Unit) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    var items = arrayListOf<Subject>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)
        val viewHolder = SubjectViewHolder(ItemSubjectBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.subject = items[position]
    }

    class SubjectViewHolder(val binding: ItemSubjectBinding) : RecyclerView.ViewHolder(binding.root)

}

