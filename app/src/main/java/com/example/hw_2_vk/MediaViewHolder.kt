package com.example.hw_2_vk

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_2_vk.items.MediaItem
import com.example.hw_2_vk.items.MediaType

class MediaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageView = view.findViewById<ImageView>(R.id.mediaView)

    fun bind(item: MediaItem) {
        if (item.type == MediaType.GIF) {
            Glide.with(imageView.context)
                .asGif()
                .fitCenter()
                .load(item.url)
                .into(imageView)

        } else {
            Glide.with(imageView.context)
                .load(item.url)
                .fitCenter()
                .into(imageView)
        }
    }

}

