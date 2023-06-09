package com.example.myfoodapp.presentation.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myfoodapp.R
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.databinding.FragmentBasketBinding

class FragmentBasket: Fragment(R.layout.fragment_basket) {

    private var _binding: FragmentBasketBinding? = null
    private val binding: FragmentBasketBinding get() = _binding!!
    private val adapter = AdapterBasket()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBasketBinding.bind(view)
        binding.recyclerMain.adapter = adapter
    }

    private fun renderData(cartItem: CartItemDbEntity) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentBasket()
    }
}