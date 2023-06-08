package com.example.myfoodapp.presentation.dishes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myfoodapp.data.model.DisheResponse

class DishesDiffUtil(
    private val oldItems: List<DisheResponse>,
    private val newItems: List<DisheResponse>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].name == newItems[newItemPosition].name
    }
}