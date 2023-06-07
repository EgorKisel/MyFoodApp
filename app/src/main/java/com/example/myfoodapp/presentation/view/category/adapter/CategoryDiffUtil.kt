package com.example.myfoodapp.presentation.view.category.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myfoodapp.data.model.CategoryKitchen

class CategoryDiffUtil(
    private val oldItems: List<CategoryKitchen>,
    private val newItems: List<CategoryKitchen>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].name != newItems[newItemPosition].name
    }
}





