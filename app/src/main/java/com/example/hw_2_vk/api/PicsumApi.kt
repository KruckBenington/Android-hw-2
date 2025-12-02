package com.example.hw_2_vk.api

import com.example.hw_2_vk.images.ImageData
import retrofit2.http.GET
import retrofit2.http.Query


interface PicsumApi {
    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<ImageData>
}