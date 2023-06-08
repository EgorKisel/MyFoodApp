package com.example.myfoodapp.presentation.product

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.myfoodapp.R
import com.example.myfoodapp.commom.KEY_DISH_BUNDLE
import com.example.myfoodapp.data.model.DisheResponse

class DialogFragmentProduct: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_fragment_product)

        val  buttonClose = dialog.findViewById<ImageView>(R.id.btnClose)
        buttonClose.setOnClickListener { dismiss() }

        val name = dialog.findViewById<TextView>(R.id.tvProductName)
        val description = dialog.findViewById<TextView>(R.id.tvProductDescription)
        val price = dialog.findViewById<TextView>(R.id.tvProductPrice)
        val weight = dialog.findViewById<TextView>(R.id.tvProductWeight)
        val image = dialog.findViewById<ImageView>(R.id.imgProduct)

        val dish: DisheResponse? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(KEY_DISH_BUNDLE, DisheResponse::class.java)
        } else {
            arguments?.getParcelable(KEY_DISH_BUNDLE)
        }
        if (dish != null) {
            name.text = dish.name
            description.text = dish.description
            price.text = dish.price.toString() + " Р"
            weight.text = dish.weight.toString() + "г"
            image.load(dish.imageUrl) {
                placeholder(R.drawable.ic_no_photo_vector)
                error(R.drawable.ic_no_photo_vector)
            }
        }
        return dialog
    }
}

