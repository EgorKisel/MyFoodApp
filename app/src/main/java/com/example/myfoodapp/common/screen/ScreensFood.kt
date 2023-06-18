package com.example.myfoodapp.common.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.myfoodapp.common.KEY_BUNDLE_TITLE
import com.example.myfoodapp.common.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.data.model.network.CategoryKitchenResponse
import com.example.myfoodapp.presentation.basket.FragmentBasket
import com.example.myfoodapp.presentation.category.FragmentCategory
import com.example.myfoodapp.presentation.dishes.FragmentMenuList
import com.example.myfoodapp.presentation.product.DialogFragmentProduct
import com.github.terrakok.cicerone.androidx.FragmentScreen

object BasketScreen: FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return FragmentBasket.newInstance()
    }
}
object CategoryScreen: FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return FragmentCategory.newInstance()
    }
}
data class DishesScreen(private val categoryKitchenResponse: CategoryKitchenResponse): FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return FragmentMenuList.newInstance(Bundle().apply {
            putInt(KEY_CATEGORIES_BUNDLE, categoryKitchenResponse.id)
            putString(KEY_BUNDLE_TITLE, categoryKitchenResponse.name)
        })
    }
}
object ProductScreen: FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return DialogFragmentProduct()
    }
}