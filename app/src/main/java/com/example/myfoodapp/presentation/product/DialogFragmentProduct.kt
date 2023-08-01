package com.example.myfoodapp.presentation.product

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.myfoodapp.R
import com.example.myfoodapp.common.KEY_DISH_BUNDLE
import com.example.myfoodapp.core.MyApp
import com.example.myfoodapp.data.model.network.DisheResponse
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.databinding.DialogFragmentProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DialogFragmentProduct : DialogFragment() {

    private var _binding: DialogFragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener { dismiss() }

        val dish: DisheResponse? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(KEY_DISH_BUNDLE, DisheResponse::class.java)
        } else {
            arguments?.getParcelable(KEY_DISH_BUNDLE)
        }
        if (dish != null) {
            binding.tvProductName.text = dish.name
            binding.tvProductDescription.text = dish.description
            binding.tvProductPrice.text = dish.price.toString()
            binding.tvProductWeight.text = dish.weight.toString()
            binding.imgProduct.load(dish.imageUrl) {
                placeholder(R.drawable.ic_no_photo_vector)
                error(R.drawable.ic_no_photo_vector)
            }
        }
        addToBasket()
    }

    private fun addToBasket() {
        binding.btnAdd.setOnClickListener {
            val dish: DisheResponse? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable(KEY_DISH_BUNDLE, DisheResponse::class.java)
            } else {
                arguments?.getParcelable(KEY_DISH_BUNDLE)
            }
            val cartItem = CartItemDbEntity(
                itemName = dish?.name ?: "",
                itemImage = dish?.imageUrl ?: "",
                itemPrice = dish?.price ?: 0,
                itemWeight = dish?.weight ?: 0
            )
            saveCartItem(cartItem)
            dismiss()
        }
    }

    private fun saveCartItem(cartItem: CartItemDbEntity) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                MyApp.getBasketItems().insertCartItem(cartItem)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), theme)
    }
}


