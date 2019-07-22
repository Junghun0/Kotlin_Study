package com.example.kotlin_study.sample06

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun imageUrl(view: ImageView, uri: String ){
    Glide.with(view)
        .load(uri)
        .into(view)
}