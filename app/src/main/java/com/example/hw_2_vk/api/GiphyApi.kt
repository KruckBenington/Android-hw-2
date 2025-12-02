package com.example.hw_2_vk.api

import com.example.hw_2_vk.images.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/search")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GiphyResponse
}