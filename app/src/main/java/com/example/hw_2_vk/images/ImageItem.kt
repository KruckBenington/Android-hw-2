package com.example.hw_2_vk.images

class ImageItem(
    val id: String,
    val imageUrl: String,
    val width: Int,
    val height: Int
)

fun ImageData.toImageItem(): ImageItem = ImageItem(
    id = id,
    imageUrl = downloadUrl,
    width = width,
    height = height
)



