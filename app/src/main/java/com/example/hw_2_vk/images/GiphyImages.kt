package com.example.hw_2_vk.images

data class GiphyResponse(
    val data: List<GifData>
)

data class GifData(
    val id: String,
    val images: GifImagesData
)

data class GifImagesData(
    val downsized: GifImageData
)

data class GifImageData(
    val url: String,
    val width: String,
    val height: String
)
