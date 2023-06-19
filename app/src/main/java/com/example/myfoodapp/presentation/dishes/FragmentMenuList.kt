package com.example.myfoodapp.presentation.dishes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myfoodapp.R
import com.example.myfoodapp.common.KEY_BUNDLE_TITLE
import com.example.myfoodapp.common.KEY_DISH_BUNDLE
import com.example.myfoodapp.common.makeToast
import com.example.myfoodapp.core.MyApp
import com.example.myfoodapp.data.model.network.DisheResponse
import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.databinding.FragmentMenuListBinding
import com.example.myfoodapp.presentation.BackPressedListener
import com.example.myfoodapp.presentation.dishes.adapter.AdapterDishes
import com.example.myfoodapp.presentation.dishes.adapter.OnDishesClickListener
import com.example.myfoodapp.presentation.product.DialogFragmentProduct
import com.github.terrakok.cicerone.Router
import com.google.android.material.chip.Chip

class FragmentMenuList : Fragment(R.layout.fragment_menu_list), OnDishesClickListener,
    BackPressedListener {

    private lateinit var binding: FragmentMenuListBinding
    private val viewModel: DishesViewModel = DishesViewModel(
        MutableLiveData(),
        RepositoryDishesImpl(),
        MutableLiveData(),
        mutableListOf(),
        MyApp.appInstance.router
    )
    private val adapter = AdapterDishes()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuListBinding.bind(view)
        val observer = Observer<DishesViewModel.DishesAppState> { renderData(it) }
        val dishesObserver = Observer<List<DisheResponse>> { dishes ->
            adapter.setData(dishes)
            adapter.mSetOnClickListener(this)
        }
        binding.recyclerDishes.adapter = adapter

        with(viewModel) {
            getLiveData().observe(viewLifecycleOwner, observer)
            getDishesLiveData().observe(viewLifecycleOwner, dishesObserver)
            getDishes()
        }

        val categoryName = arguments?.getString(KEY_BUNDLE_TITLE)
        binding.tvDishes.text = categoryName.toString()

        backToCategories()
        dishesFiltering()
    }

    private fun backToCategories() {
        binding.icBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun dishesFiltering() {
        binding.chipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val selectedChip = chipGroup.findViewById<Chip>(checkedId)
            val selectedTag = selectedChip?.text?.toString()
            if (selectedTag != null) {
                viewModel.filterDishesByTag(selectedTag)
            }
        }
    }

    private fun renderData(appState: DishesViewModel.DishesAppState) {
        when (appState) {
            is DishesViewModel.DishesAppState.Error -> {}
            DishesViewModel.DishesAppState.Loading -> {}
            is DishesViewModel.DishesAppState.Success -> {
                adapter.setData(appState.dishes.dishes)
            }

            DishesViewModel.DishesAppState.EmptyList -> {
                makeToast(R.string.empty_list)
            }
        }
    }

    companion object {
        const val PRODUCT_DIALOG_TAG = "PRODUCT_DIALOG_TAG"
        fun newInstance(bundle: Bundle): FragmentMenuList {
            val fragment = FragmentMenuList()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onItemClick(dishes: DisheResponse) {
        val args = Bundle().apply {
            putParcelable(KEY_DISH_BUNDLE, dishes)
        }
        val dialogFragment = DialogFragmentProduct()
        dialogFragment.show(parentFragmentManager, PRODUCT_DIALOG_TAG)
        dialogFragment.arguments = args
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBackPressed()
        return true
    }
}

