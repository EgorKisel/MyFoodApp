package com.example.myfoodapp.presentation.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.myfoodapp.data.model.network.CategoryKitchenResponse
import com.example.myfoodapp.databinding.FragmentMainRecyclerItemBinding

class AdapterCategory(private var data: List<CategoryKitchenResponse> = listOf()) :
    RecyclerView.Adapter<AdapterCategory.ViewHolderMain>() {

    private lateinit var onItemClickListener: OnItemClickListener

    fun mSetOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setData(dataNew: List<CategoryKitchenResponse>) {
        val diffUtil = CategoryDiffUtil(this.data, dataNew)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.data = dataNew
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolderMain(itemView: View) : ViewHolder(itemView) {
        fun bind(categoryKitchenResponse: CategoryKitchenResponse) {
            val binding = FragmentMainRecyclerItemBinding.bind(itemView)
            binding.textView.text = categoryKitchenResponse.name
            binding.imageView.load(categoryKitchenResponse.imageUrl) {
                transformations(RoundedCornersTransformation(25f))
            }
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(categoryKitchenResponse)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        val binding = FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderMain(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        holder.bind(data[position])
    }
}