package com.example.myfoodapp.presentation.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.databinding.FragmentBasketItemBinding

class AdapterBasket(private var data: List<CartItemDbEntity> = listOf()) :
    RecyclerView.Adapter<AdapterBasket.ViewHolderBasket>() {

    fun setData(dataNew: List<CartItemDbEntity>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class ViewHolderBasket(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cartItem: CartItemDbEntity) {
            val binding = FragmentBasketItemBinding.bind(itemView)
            binding.imgCart.load(cartItem.itemImage)
            binding.tvCartProductName.text = cartItem.itemName
            binding.tvCartProductPrice.text = cartItem.itemPrice.toString()
            binding.tvCartProductWeight.text = cartItem.itemWeight.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBasket {
        val binding = FragmentBasketItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolderBasket(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderBasket, position: Int) {
        holder.bind(data[position])
    }
}