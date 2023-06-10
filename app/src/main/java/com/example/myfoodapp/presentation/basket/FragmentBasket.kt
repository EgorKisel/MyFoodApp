package com.example.myfoodapp.presentation.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.R
import com.example.myfoodapp.common.convertDishesToCartItems
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.databinding.FragmentBasketBinding

class FragmentBasket : Fragment(R.layout.fragment_basket), OnItemClickListener {

    private var _binding: FragmentBasketBinding? = null
    private val binding: FragmentBasketBinding get() = _binding!!
    private val adapter = AdapterBasket()
    private val viewModel: BasketViewModel by lazy {
        ViewModelProvider(this)[BasketViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBasketBinding.bind(view)
        binding.recyclerMain.adapter = adapter
        adapter.mSetOnClickListener(this)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllBasket()
    }

    private fun renderData(appState: BasketViewModel.AppState) {
        when (appState) {
            is BasketViewModel.AppState.Error -> {}
            BasketViewModel.AppState.Loading -> {}
            is BasketViewModel.AppState.Success -> {
                val basketItems = convertDishesToCartItems(appState.basket)
                adapter.setData(basketItems)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentBasket()
    }

    override fun onAddToBasket(cartItem: CartItemDbEntity) {
        //viewModel.insertItem()
    }

    override fun onRemoveFromBasket(cartItem: CartItemDbEntity) {
        //viewModel.deleteItem()
    }
}