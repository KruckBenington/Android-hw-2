package com.example.hw_1_vk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyAdapter() : RecyclerView.Adapter<MyViewHolder>() {


    private var items: List<NumberData> = emptyList()

    fun addItem(newItems: List<NumberData>) {
        val differ = MyDifferCallback(this.items, newItems = newItems)
        val result = DiffUtil.calculateDiff(differ)
        items = newItems
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.square, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val index = items[position]

        val colorToSet = if (position % 2 == 1) ButtonColor.Blue else ButtonColor.Red
        holder.setColor(colorToSet)

        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.count()
    }

}

class MyDifferCallback(val oldItems: List<NumberData>, val newItems: List<NumberData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}
