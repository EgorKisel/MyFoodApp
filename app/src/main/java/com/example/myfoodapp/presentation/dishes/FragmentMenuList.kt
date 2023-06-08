package com.example.myfoodapp.presentation.dishes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.R
import com.example.myfoodapp.commom.KEY_BUNDLE_TITLE
import com.example.myfoodapp.commom.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.commom.KEY_DISH_BUNDLE
import com.example.myfoodapp.data.model.DisheResponse
import com.example.myfoodapp.databinding.FragmentMenuListBinding
import com.example.myfoodapp.presentation.dishes.adapter.AdapterDishes
import com.example.myfoodapp.presentation.dishes.adapter.OnDishesClickListener
import com.example.myfoodapp.presentation.product.DialogFragmentProduct
import com.google.android.material.chip.Chip

class FragmentMenuList : Fragment(R.layout.fragment_menu_list), OnDishesClickListener {

    private var _binding: FragmentMenuListBinding? = null
    private val binding: FragmentMenuListBinding get() = _binding!!
    private val viewModel: DishesViewModel by lazy {
        ViewModelProvider(this)[DishesViewModel::class.java]
    }
    private val adapter = AdapterDishes()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuListBinding.bind(view)
        val observer = Observer<DishesViewModel.DishesAppState> { renderData(it) }
        val dishesObserver = Observer<List<DisheResponse>> { dishes ->
            adapter.setData(dishes)
            adapter.mSetOnClickListener(this)
        }
        binding.recyclerDishes.adapter = adapter
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            getDishesLiveData().observe(viewLifecycleOwner, dishesObserver)
            getDishes2()
        }
        val categoryId = arguments?.getInt(KEY_CATEGORIES_BUNDLE)
        val categoryName = arguments?.getString(KEY_BUNDLE_TITLE)
        binding.tvDishes.text = categoryName.toString()

        dishesFiltering()
    }

    private fun dishesFiltering() {
        binding.chipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val selectedChip = chipGroup.findViewById<Chip>(checkedId)
            val selectedTag = selectedChip?.text?.toString()
            if (selectedTag != null) {
                viewModel.onChipSelected(selectedTag)
                viewModel.filterDishesByTag()
            }
        }
    }

    private fun renderData(appState: DishesViewModel.DishesAppState) {
        when(appState) {
            is DishesViewModel.DishesAppState.Error -> {}
            DishesViewModel.DishesAppState.Loading -> {}
            is DishesViewModel.DishesAppState.Success -> {
                adapter.setData(appState.dishes.dishes)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
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
        dialogFragment.show(parentFragmentManager, "dialog_tag")
        dialogFragment.arguments = args
    }
}

