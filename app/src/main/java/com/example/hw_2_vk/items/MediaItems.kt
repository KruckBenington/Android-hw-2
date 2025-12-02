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


    val realAspect = height.toFloat() / width.toFloat()


    val aspect = realAspect.coerceIn(0.7f, 1.8f)

    val targetHeight = (targetWidth * aspect).toInt()

    val displayUrl = "https://picsum.photos/id/$id/$targetWidth/$targetHeight"

    return MediaItem(
        id = id,
        url = displayUrl,
        width = targetWidth,
        height = targetHeight,
        type = MediaType.IMAGE
    )
}


fun GifData.toMediaItem(): MediaItem {
    val img = images.downsized

    return MediaItem(
        id = id,
        url = img.url,
        width = img.width.toInt(),
        height = img.height.toInt(),
        type = MediaType.GIF
    )
}