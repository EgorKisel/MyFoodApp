package com.example.myfoodapp.domain

import com.example.myfoodapp.presentation.category.CategoryViewModel

interface RepositoryCategory {
    fun getCategories(callback: CategoryViewModel.Callback)
}