package com.example.myfoodapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentMenuListItemBinding
import com.example.myfoodapp.response.dishes.Dishe

class AdapterDishes(private var data: List<Dishe> = listOf()) :
    RecyclerView.Adapter<AdapterDishes.ViewHolderDishes>() {

    fun setData(dataNew: List<Dishe>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class ViewHolderDishes(itemView: View) : ViewHolder(itemView) {
        fun bind(dishe: Dishe) {
            val binding = FragmentMenuListItemBinding.bind(itemView)
            binding.tvDishes.text = dishe.name
            binding.imgDishes.load(dishe.imageUrl) {
                placeholder(R.drawable.ic_no_photo_vector)
                error(R.drawable.ic_no_photo_vector)
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