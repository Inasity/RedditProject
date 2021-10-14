package com.example.android.redditproject.main

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.redditproject.databinding.ListItemRedditfeedBinding
import com.example.android.redditproject.network.Child

class ListingAdapter : ListAdapter<Child, ListingAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: ListItemRedditfeedBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            binding.child = child
            binding.executePendingBindings()
            binding.viewBrowser.text = Html.fromHtml("<a href=${child.data.url}>View In Browser</a>")

            binding.viewBrowser.setOnClickListener {view: View ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(child.data.url)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemRedditfeedBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val childFeed = getItem(position)
        if(childFeed.data.selftext.isNotEmpty())
        {
            holder.itemView.setOnClickListener {v: View ->

                val dialogBuilderSelftext = AlertDialog.Builder(v.rootView.context)
                dialogBuilderSelftext.setCancelable(false)
                dialogBuilderSelftext.setMessage(childFeed.data.selftext)
                dialogBuilderSelftext.apply {
                    setPositiveButton("OK"
                    ) { dialog, _ ->
                        dialog.dismiss()
                    }
                }
                dialogBuilderSelftext.show()
            }
        }

        holder.bind(childFeed)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Child>() {
        override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem.data.id == newItem.data.id
        }
    }
}