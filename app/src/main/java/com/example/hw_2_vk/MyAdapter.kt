package com.example.hw_2_vk

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_2_vk.items.MediaItem

class MyAdapter() : RecyclerView.Adapter<MediaViewHolder>() {

    private var items: List<MediaItem> = emptyList()

    fun submitItems(newItems: List<MediaItem>) {
        val diff = MyDifferCallback(this.items, newItems)
        val result = DiffUtil.calculateDiff(diff)
        items = newItems
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MediaViewHolder {
        return MediaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.media, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "${holder.bindingAdapterPosition + 1}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun getItemCount(): Int {
        return items.count()
    }

}

class MyDifferCallback(val oldItems: List<MediaItem>, val newItems: List<MediaItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}
