package com.example.myfoodapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.KEY_BUNDLE
import com.example.myfoodapp.databinding.FragmentMenuListBinding
import com.example.myfoodapp.viewmodel.DishesAppState
import com.example.myfoodapp.viewmodel.DishesViewModel
import com.google.android.material.chip.Chip

class FragmentMenuList : Fragment() {

    private var _binding: FragmentMenuListBinding? = null
    private val binding: FragmentMenuListBinding get() = _binding!!
    private val viewModel: DishesViewModel by lazy {
        ViewModelProvider(this)[DishesViewModel::class.java]
    }
    private val adapter = AdapterDishes()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<DishesAppState> { renderData(it) }
        binding.recyclerDishes.adapter = adapter
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            getDishes()
        }
//        val categoryName = arguments?.getInt(KEY_BUNDLE)
//        binding.tvDishes.text = categoryName.toString()
    }

    private fun renderData(appState: DishesAppState) {
        when(appState) {
            is DishesAppState.Error -> {}
            DishesAppState.Loading -> {}
            is DishesAppState.Success -> {
                adapter.setData(appState.dishes.dishes)
                binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
                    val selectedChip = view?.findViewById<Chip>(checkedId)
                    val selectedTag = selectedChip?.text?.toString()

                    if (selectedTag == "Всё меню") {
                        // Если выбран чип "Всё меню", показываем все блюда без фильтрации
                        adapter.setData(appState.dishes.dishes)
                    } else {
                        // Фильтруем список блюд по выбранному тегу
                        val filteredDishes = appState.dishes.dishes.filter { dish ->
                            dish.tegs.contains(selectedTag)
                        }
                        adapter.setData(filteredDishes)
                    }
                }
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