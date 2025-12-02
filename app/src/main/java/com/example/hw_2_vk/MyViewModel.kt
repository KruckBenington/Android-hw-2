package com.example.hw_2_vk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_2_vk.api.NetworkModule
import com.example.hw_2_vk.items.MediaItem
import com.example.hw_2_vk.items.toMediaItem

import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val mediaItems = MutableLiveData<List<MediaItem>>(emptyList())
    val isLoading = MutableLiveData(false)
    val isPagingLoading = MutableLiveData(false)
    val errorMessage = MutableLiveData<String?>(null)

    private var currentPage = 1
    private val pageSize = 30
    private var isLastPage = false

    fun loadFirstPage() {
        currentPage = 1
        isLastPage = false
        loadPage(page = 1, isFirstPage = true)
    }

    fun loadNextPage() {
        if (isPagingLoading.value == true || isLastPage) return

        val nextPage = currentPage + 1
        loadPage(page = nextPage, isFirstPage = false)
    }

    private fun loadPage(page: Int, isFirstPage: Boolean) {
        viewModelScope.launch {
            try {
                if (isFirstPage) {
                    isLoading.value = true
                    errorMessage.value = null
                } else {
                    isPagingLoading.value = true
                }

                val dtos = NetworkModule.picsumApi.getImages(
                    page = page,
                    limit = pageSize
                )

                val newItems = dtos.map { it.toMediaItem() }

                if (isFirstPage) {
                    mediaItems.value = newItems
                } else {
                    val current = mediaItems.value.orEmpty()
                    mediaItems.value = current + newItems
                }


                currentPage = page


                if (newItems.size < pageSize) {
                    isLastPage = true
                }

            } catch (e: Exception) {
                if (isFirstPage) {
                    errorMessage.value = "Ошибка загрузки"
                }

            } finally {
                if (isFirstPage) {
                    isLoading.value = false
                } else {
                    isPagingLoading.value = false
                }
            }
        }
    }



}