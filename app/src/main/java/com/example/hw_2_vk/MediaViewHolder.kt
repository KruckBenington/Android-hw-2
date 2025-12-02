package com.example.hw_2_vk

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_2_vk.items.MediaItem

class MediaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageView = view.findViewById<ImageView>(R.id.mediaView)

    fun bind(item: MediaItem) {
        // Пока только картинки (IMAGE); GIF добавлю позже
        Glide.with(imageView.context)
            .load(item.url)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }

}

