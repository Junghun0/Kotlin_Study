package com.example.kotlin_study.sample06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_study.R
import com.example.kotlin_study.databinding.Viewpager2layoutBinding

class ViewpagerAdapter: RecyclerView.Adapter<ViewpagerAdapter.ViewPagerViewHolder>() {

    var items = arrayListOf<ViewpagerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewpager2layout, parent, false)
        val binding = Viewpager2layoutBinding.bind(view)

        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.binding.data = items[position]
    }

    class ViewPagerViewHolder(val binding: Viewpager2layoutBinding): RecyclerView.ViewHolder(binding.root)

}
