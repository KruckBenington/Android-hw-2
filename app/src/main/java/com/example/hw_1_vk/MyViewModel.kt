package com.example.hw_1_vk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val numbers = MutableLiveData<List<NumberData>>(emptyList())

    fun addNumber() {
        val current = numbers.value ?: emptyList()
        val newItem = NumberData(current.size + 1)
        numbers.value = current + newItem
    }
}