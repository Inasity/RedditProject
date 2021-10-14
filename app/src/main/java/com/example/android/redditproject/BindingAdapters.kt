package com.example.android.redditproject

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.redditproject.main.ListingAdapter
import com.example.android.redditproject.network.Child

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Child>?) {
    val adapter = recyclerView.adapter as ListingAdapter
    adapter.submitList(data)
}

@BindingAdapter("authorName")
fun bindName(textView: TextView, authorName: String?)
{
    authorName?.let {
        val authorTitle = "Author: $authorName"
        textView.text = authorTitle
    }
}

@BindingAdapter("numOfComments")
fun bindComments(textView: TextView, comments: Int?)
{
    comments?.let{
        val numComments = "$comments Comments"
        textView.text = numComments
    }
}
