package com.example.myfoodapp.presentation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myfoodapp.R
import com.example.myfoodapp.common.KEY_BUNDLE_TITLE
import com.example.myfoodapp.common.KEY_CATEGORIES_BUNDLE
import com.example.myfoodapp.common.getCurrentDate
import com.example.myfoodapp.core.MyApp
import com.example.myfoodapp.data.model.network.CategoryKitchenResponse
import com.example.myfoodapp.data.repoimpl.RepositoryCategoryImpl
import com.example.myfoodapp.databinding.FragmentMainBinding
import com.example.myfoodapp.presentation.base.viewBinding
import com.example.myfoodapp.presentation.category.adapter.AdapterCategory
import com.example.myfoodapp.presentation.category.adapter.OnItemClickListener

class FragmentCategory : Fragment(R.layout.fragment_main), OnItemClickListener {

    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel: CategoryViewModel = CategoryViewModel(
        MutableLiveData(),
        RepositoryCategoryImpl(), MyApp.appInstance.router
    )
    private val adapter = AdapterCategory()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<CategoryViewModel.AppState> { renderData(it) }
        binding.recyclerMain.adapter = adapter
        adapter.mSetOnClickListener(this)
        binding.tvData.text = getCurrentDate()
        with(viewModel) {
            getLiveData().observe(viewLifecycleOwner, observer)
            getCategories()
        }
    }

    private fun renderData(state: CategoryViewModel.AppState) {
        when (state) {
            is CategoryViewModel.AppState.Error -> {}
            CategoryViewModel.AppState.Loading -> {}
            is CategoryViewModel.AppState.Success -> {
                adapter.setData(state.categoryDTO.categories)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCategory()
    }

    override fun onItemClick(categoryKitchenResponse: CategoryKitchenResponse) {
        val bundle = Bundle().apply {
            putInt(KEY_CATEGORIES_BUNDLE, categoryKitchenResponse.id)
            putString(KEY_BUNDLE_TITLE, categoryKitchenResponse.name)
        }
        viewModel.openDishesScreen(categoryKitchenResponse)
    }
}