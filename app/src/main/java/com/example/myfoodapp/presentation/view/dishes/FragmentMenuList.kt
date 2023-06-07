package com.example.myfoodapp.presentation.view.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.common.Const.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.common.Const.KEY_TITLE
import com.example.myfoodapp.databinding.FragmentMenuListBinding
import com.google.android.material.chip.Chip

class FragmentMenuList : Fragment() {

    private var _binding: FragmentMenuListBinding? = null
    private val binding: FragmentMenuListBinding get() = _binding!!
    private val viewModel: DishesViewModel by lazy {
        ViewModelProvider(this)[DishesViewModel::class.java]
    }
    private val adapter = AdapterDishes()


    //Удалить метод
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //СДелать как в том фрагшменте
        val observer = Observer<DishesViewModel.DishesAppState> { renderData(it) }
        binding.recyclerDishes.adapter = adapter

        val categoryId = arguments?.getInt(KEY_CATEGORIES_BUNDLE)
        val categoryName = arguments?.getString(KEY_TITLE)

        binding.tvDishes.text = categoryName.toString()

        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            getDishes(categoryId)
        }


        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedChip = view.findViewById<Chip>(checkedId)
            val selectedTag = selectedChip?.text?.toString()
            viewModel.onChipCliked(selectedTag)
        }

    }

    private fun renderData(appState: DishesViewModel.DishesAppState) {
        when (appState) {
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
}