package com.example.hw_2_vk.items

import com.example.hw_2_vk.images.GifData
import com.example.hw_2_vk.images.ImageData

enum class MediaType {
    IMAGE, GIF
}

data class MediaItem(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val type: MediaType
)

fun ImageData.toMediaItem(): MediaItem {

    val targetWidth = 600
    val aspect = height.toFloat() / width.toFloat()
    val targetHeight = (targetWidth * aspect).toInt()


    val displayUrl = "https://picsum.photos/id/$id/$targetWidth/$targetHeight"
    return MediaItem(
        id = id,
        url = displayUrl,
        width = width,
        height = height,
        type = MediaType.IMAGE
    )
}


fun GifData.toMediaItem(): MediaItem {
    val downsized = images.downsized

    val w = downsized.width.toIntOrNull() ?: 1
    val h = downsized.height.toIntOrNull() ?: 1

    return MediaItem(
        id = id,
        url = downsized.url,
        width = w,
        height = h,
        type = MediaType.GIF
    )
}