package com.example.myfoodapp.presentation.dishes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.R
import com.example.myfoodapp.common.KEY_BUNDLE_TITLE
import com.example.myfoodapp.common.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.common.KEY_DISH_BUNDLE
import com.example.myfoodapp.data.model.DisheResponse
import com.example.myfoodapp.databinding.FragmentMenuListBinding
import com.example.myfoodapp.presentation.dishes.adapter.AdapterDishes
import com.example.myfoodapp.presentation.dishes.adapter.OnDishesClickListener
import com.example.myfoodapp.presentation.product.ProguctDialog
import com.google.android.material.chip.Chip

class FragmentMenuList : Fragment(R.layout.fragment_menu_list), OnDishesClickListener {

    private lateinit var binding: FragmentMenuListBinding

    private val viewModel: DishesViewModel by lazy {
        ViewModelProvider(this)[DishesViewModel::class.java]
    }
    private val adapter = AdapterDishes()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMenuListBinding.bind(view)


        binding.recyclerDishes.adapter = adapter

        //Не красиво
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner) { renderData(it) }
            getDishesLiveData().observe(viewLifecycleOwner) { dishes ->
                adapter.setData(dishes)
                adapter.mSetOnClickListener(this@FragmentMenuList)
            }
            //Переименвоать
            getDishes2()
        }

        //Удалить
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
                Toast.makeText(requireContext(), getString(R.string.empty_list), Toast.LENGTH_LONG)
                    .show()
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
        val dialogFragment = ProguctDialog()
        dialogFragment.show(parentFragmentManager, PRODUCT_DIALOG_TAG) // Константы
        dialogFragment.arguments = args
    }
}

