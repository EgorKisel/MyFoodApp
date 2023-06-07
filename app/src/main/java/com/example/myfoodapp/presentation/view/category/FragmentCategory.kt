package com.example.myfoodapp.presentation.view.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.R
import com.example.myfoodapp.common.Const.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.common.Const.KEY_TITLE
import com.example.myfoodapp.common.getCurrentDate
import com.example.myfoodapp.common.openDetails
import com.example.myfoodapp.data.model.CategoryKitchen
import com.example.myfoodapp.databinding.FragmentMainBinding
import com.example.myfoodapp.presentation.view.category.adapter.AdapterMain
import com.example.myfoodapp.presentation.view.category.adapter.OnItemClickListener
import com.example.myfoodapp.presentation.view.dishes.FragmentMenuList

class FragmentCategory : Fragment(R.layout.fragment_main), OnItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val adapter = AdapterMain()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        binding.recyclerMain.adapter = adapter
        adapter.mSetOnClickListener(this)
        binding.tvData.text = getCurrentDate()

        with(viewModel) {
            getLiveData().observe(viewLifecycleOwner) { renderData(it) }
            getCategories()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(categoryKitchen: CategoryKitchen) {
        Bundle().apply {
            putInt(KEY_CATEGORIES_BUNDLE, categoryKitchen.id)
            putString(KEY_TITLE, categoryKitchen.name)
            this@FragmentCategory.openDetails(FragmentMenuList.newInstance(this),
                R.id.fragmentContainerMain)
        }
    }

    private fun renderData(state: MainViewModel.AppState) {
        when (state) {
            is MainViewModel.AppState.Error -> {
                //TODO Toast
            }
            is MainViewModel.AppState.Success -> {
                adapter.setData(state.categoryDTO.categories)
            }
            MainViewModel.AppState.Loading -> {}
        }
    }

    companion object {
        fun newInstance() = FragmentCategory()
    }
}