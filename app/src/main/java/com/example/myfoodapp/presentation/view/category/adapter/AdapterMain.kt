package com.example.myfoodapp.presentation.view.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.databinding.FragmentMainRecyclerItemBinding
import com.example.myfoodapp.data.model.CategoryKitchen
import com.example.myfoodapp.presentation.view.category.ViewHolderMain

class AdapterMain(private var data: List<CategoryKitchen> = listOf()) :
    RecyclerView.Adapter<ViewHolderMain>() {

    private lateinit var onItemClickListener: OnItemClickListener

    fun mSetOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setData(dataNew: List<CategoryKitchen>) {
        val diffUtil = CategoryDiffUtil(this.data, dataNew)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.data = dataNew
        diffResult.dispatchUpdatesTo(this)
    }

//    var sources: List<Sources> = emptyList()
//        set(value) {
//            var diffUtil = SourcesDiffUtil(field, value)
//            var diffResult = DiffUtil.calculateDiff(diffUtil)
//            field = value
//            diffResult.dispatchUpdatesTo(this)
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        val binding = FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderMain(binding.root,onItemClickListener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        holder.bind(data[position])
    }
}