package com.example.hw_1_vk

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val square = view.findViewById<TextView>(R.id.square_text)


    fun bind(data : NumberData) {
        square.text = data.number.toString()
    }

    fun setColor(buttonColor: ButtonColor) {
        val color = when (buttonColor) {
            ButtonColor.Red ->  R.color.square_even
            ButtonColor.Blue -> R.color.square_odd
        }

        square.setBackgroundResource(color)
    }




}

