package com.example.hw_2_vk.images

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    val data: List<GifData>
)

data class GifData(
    val id: String,
    val images: GifImagesData
)

data class GifImagesData(
    @SerializedName("downsized")
    val downsized: GifImageData
)

data class GifImageData(
    val url: String,
    val width: String,
    val height: String
)
