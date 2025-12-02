package com.example.hw_2_vk.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object NetworkModule {

    private const val PICSUM_BASE_URL = "https://picsum.photos/"
    private const val GIPHY_BASE_URL = "https://api.giphy.com/"

    val picsumApi: PicsumApi by lazy {
        Retrofit.Builder()
            .baseUrl(PICSUM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PicsumApi::class.java)
    }


    val giphyApi by lazy {
        Retrofit.Builder()
            .baseUrl(GIPHY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GiphyApi::class.java)
    }
}

    