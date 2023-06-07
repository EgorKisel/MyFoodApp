package com.example.myfoodapp.presentation.view.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myfoodapp.data.model.CategoryKitchen
import com.example.myfoodapp.databinding.FragmentMainRecyclerItemBinding
import com.example.myfoodapp.presentation.view.category.adapter.OnItemClickListener

class ViewHolderMain(itemView: View, private val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    fun bind(categoryKitchen: CategoryKitchen) {
        val binding = FragmentMainRecyclerItemBinding.bind(itemView)
        binding.textView.text = categoryKitchen.name
        binding.imageView.load(categoryKitchen.imageUrl)
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(categoryKitchen)
        }
    }
}