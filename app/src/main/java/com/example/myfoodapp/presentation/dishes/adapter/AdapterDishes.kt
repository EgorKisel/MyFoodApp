package com.example.myfoodapp.presentation.dishes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.myfoodapp.R
import com.example.myfoodapp.data.model.network.DisheResponse
import com.example.myfoodapp.databinding.FragmentMenuListItemBinding

class AdapterDishes(private var data: List<DisheResponse> = listOf()) :
    RecyclerView.Adapter<AdapterDishes.ViewHolderDishes>() {

    private lateinit var onItemClickListener: OnDishesClickListener

    fun mSetOnClickListener(listener: OnDishesClickListener) {
        onItemClickListener = listener
    }

    fun setData(dataNew: List<DisheResponse>) {
        val diffUtil = DishesDiffUtil(this.data, dataNew)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.data = dataNew
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolderDishes(itemView: View) : ViewHolder(itemView) {
        fun bind(disheResponse: DisheResponse) {
            val binding = FragmentMenuListItemBinding.bind(itemView)
            binding.tvDishes.text = disheResponse.name
            binding.imgDishes.load(disheResponse.imageUrl) {
                placeholder(R.drawable.ic_no_photo_vector)
                error(R.drawable.ic_no_photo_vector)
            }
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(disheResponse)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDishes {
        val binding = FragmentMenuListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolderDishes(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderDishes, position: Int) {
        holder.bind(data[position])
    }
}