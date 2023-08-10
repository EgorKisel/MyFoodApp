package com.example.myfoodapp.presentation.base

import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.myfoodapp.data.model.network.CategoryKitchenResponse
import com.example.myfoodapp.databinding.FragmentMainRecyclerItemBinding
import com.example.myfoodapp.presentation.category.adapter.OnItemClickListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object AdapterDelegates {

    private lateinit var onItemClickListener: OnItemClickListener

    fun mSetOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    val categoryDelegate =
        adapterDelegateViewBinding<CategoryKitchenResponse, ListItem, FragmentMainRecyclerItemBinding>(
            { inflater, container ->
                FragmentMainRecyclerItemBinding.inflate(
                    inflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                binding.textView.text = item.name
                binding.imageView.load(item.imageUrl) {
                    transformations(RoundedCornersTransformation(25f))
                }
                binding.root.setOnClickListener {
                    onItemClickListener.onItemClick(item)
                }
            }
        }
}