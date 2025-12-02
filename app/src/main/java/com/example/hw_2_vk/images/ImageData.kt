package com.example.hw_2_vk.images

import com.google.gson.annotations.SerializedName

data class ImageData(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)


