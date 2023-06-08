package com.example.myfoodapp.presentation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.R
import com.example.myfoodapp.commom.KEY_BUNDLE_TITLE
import com.example.myfoodapp.commom.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.commom.getCurrentDate
import com.example.myfoodapp.commom.openDetails
import com.example.myfoodapp.data.model.CategoryKitchenResponse
import com.example.myfoodapp.databinding.FragmentMainBinding
import com.example.myfoodapp.presentation.category.adapter.AdapterCategory
import com.example.myfoodapp.presentation.category.adapter.OnItemClickListener
import com.example.myfoodapp.presentation.dishes.FragmentMenuList

class FragmentCategory : Fragment(R.layout.fragment_main), OnItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter = AdapterCategory()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        val observer = Observer<MainViewModel.AppState> { renderData(it) }
        binding.recyclerMain.adapter = adapter
        adapter.mSetOnClickListener(this)
        binding.tvData.text = getCurrentDate()
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            getCategories()
        }
    }

    private fun renderData(state: MainViewModel.AppState) {
        when (state) {
            is MainViewModel.AppState.Error -> {}
            MainViewModel.AppState.Loading -> {}
            is MainViewModel.AppState.Success -> {
                adapter.setData(state.categoryDTO.categories)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCategory()
    }

    override fun onItemClick(categoryKitchenResponse: CategoryKitchenResponse) {
        Bundle().apply {
            putInt(KEY_CATEGORIES_BUNDLE, categoryKitchenResponse.id)
            putString(KEY_BUNDLE_TITLE, categoryKitchenResponse.name)
            this@FragmentCategory.openDetails(
                FragmentMenuList.newInstance(this),
                R.id.fragmentContainerMain
            )
        }
    }
}