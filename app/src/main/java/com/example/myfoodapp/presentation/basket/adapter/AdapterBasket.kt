package com.example.myfoodapp.presentation.basket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.databinding.FragmentBasketItemBinding

class AdapterBasket(
    private var data: List<CartItemDbEntity> = listOf()
) :
    RecyclerView.Adapter<AdapterBasket.ViewHolderBasket>() {

    private lateinit var onItemClickListener: OnItemClickListener
    fun mSetOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setData(dataNew: List<CartItemDbEntity>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class ViewHolderBasket(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val quantityKey = "quantity_${adapterPosition}"

        fun bind(cartItem: CartItemDbEntity) {
            val binding = FragmentBasketItemBinding.bind(itemView)
            binding.imgCart.load(cartItem.itemImage)
            binding.tvCartProductName.text = cartItem.itemName
            binding.tvCartProductPrice.text = cartItem.itemPrice.toString() + "Р"
            binding.tvCartProductWeight.text = cartItem.itemWeight.toString() + "г"

            binding.tvQuantity.text = cartItem.quantity.toString()
            binding.btnAdd.setOnClickListener {
                onItemClickListener.onAddToBasket(cartItem)
            }
            binding.btnRemove.setOnClickListener {
                    onItemClickListener.onRemoveFromBasket(cartItem)
            }
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