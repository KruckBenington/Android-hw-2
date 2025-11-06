package com.example.hw_1_vk

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.animation.core.animateDpAsState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var buttonAddSquare : Button
    val adapter = MyAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        recyclerView = findViewById(R.id.recycler)
        buttonAddSquare = findViewById<Button>(R.id.addButton)


        val columns = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 3
            Configuration.ORIENTATION_LANDSCAPE -> 4
            else -> 1
        }

        recyclerView.layoutManager =
            GridLayoutManager(this, columns)
        recyclerView.adapter = adapter


        viewModel.numbers.observe(this) {
            adapter.addItem(it)
        }

        buttonAddSquare.setOnClickListener {
            viewModel.addNumber()
        }



    }
}



