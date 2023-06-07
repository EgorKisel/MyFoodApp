package com.example.myfoodapp.presentation.view.dishes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentMenuListItemBinding
import com.example.myfoodapp.data.model.DishResponse

class AdapterDishes(private var data: List<DishResponse> = listOf()) :
    RecyclerView.Adapter<AdapterDishes.ViewHolderDishes>() {

    fun setData(dataNew: List<DishResponse>) {
        this.data = dataNew
        //TODO DIFF UTIL
        notifyDataSetChanged()
    }

    inner class ViewHolderDishes(itemView: View) : ViewHolder(itemView) {
        fun bind(dishResponse: DishResponse) {
            val binding = FragmentMenuListItemBinding.bind(itemView)
            binding.tvDishes.text = dishResponse.name
            binding.imgDishes.load(dishResponse.imageUrl) {
                placeholder(R.drawable.ic_no_photo_vector)
                error(R.drawable.ic_no_photo_vector)
            }
        }
    }


    //ВЫнести VIEW HOLDEr
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDishes {
        val binding = FragmentMenuListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolderDishes(binding.root) // Прокинуть клик найти решение с лямдой
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderDishes, position: Int) {
        holder.bind(data[position])
    }
}