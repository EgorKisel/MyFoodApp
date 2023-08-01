package com.example.myfoodapp.presentation.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.R
import com.example.myfoodapp.common.makeToast
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.databinding.FragmentBasketBinding
import com.example.myfoodapp.presentation.base.viewBinding
import com.example.myfoodapp.presentation.basket.adapter.AdapterBasket
import com.example.myfoodapp.presentation.basket.adapter.OnItemClickListener

class FragmentBasket : Fragment(R.layout.fragment_basket), OnItemClickListener {

    private val binding by viewBinding { FragmentBasketBinding.bind(it) }

    private val adapter = AdapterBasket()
    private val viewModel: BasketViewModel by lazy {
        ViewModelProvider(this)[BasketViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMain.adapter = adapter
        adapter.mSetOnClickListener(this)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllBasket()
    }

    private fun renderData(appState: BasketViewModel.AppState) {
        when (appState) {
            is BasketViewModel.AppState.Error -> {
                makeToast(R.string.something_went_wrong)
            }

            BasketViewModel.AppState.Loading -> {}
            is BasketViewModel.AppState.Success -> {
                val basketItems = appState.basket
                val items = basketItems.map {
                    CartItemDbEntity(
                        it.id,
                        it.itemName,
                        it.itemImage,
                        it.itemPrice,
                        it.itemWeight,
                        it.quantity
                    )
                }
                adapter.setData(basketItems)
                val totalPrice = viewModel.calculateTotalPrice()
                binding.btnPay.text = totalPrice.toString() + "Р"
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentBasket()
    }

    override fun onAddToBasket(cartItem: CartItemDbEntity) {
        val sum = cartItem.quantity + 1
        viewModel.updateCartItemQuantity(cartItem.id, sum)
    }

    override fun onRemoveFromBasket(cartItem: CartItemDbEntity) {
        if (cartItem.quantity > 1) {
            viewModel.updateCartItemQuantity(cartItem.id, --cartItem.quantity)
        } else viewModel.deleteItemById(cartItem.id)
    }
}
