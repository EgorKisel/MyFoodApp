package com.example.myfoodapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.myfoodapp.databinding.FragmentMainRecyclerItemBinding
import com.example.myfoodapp.response.categories.CategoryKitchen

class AdapterMain(private var data: List<CategoryKitchen> = listOf()) :
    RecyclerView.Adapter<AdapterMain.ViewHolderMain>() {

    private lateinit var onItemClickListener: OnItemClickListener

    fun mSetOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setData(dataNew: List<CategoryKitchen>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class ViewHolderMain(itemView: View) : ViewHolder(itemView) {
        fun bind(categoryKitchen: CategoryKitchen) {
            val binding = FragmentMainRecyclerItemBinding.bind(itemView)
            binding.textView.text = categoryKitchen.name
            binding.imageView.load(categoryKitchen.imageUrl) {
                transformations(RoundedCornersTransformation(25f))
            }
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(categoryKitchen)
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