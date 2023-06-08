package com.example.myfoodapp.domain

import com.example.myfoodapp.presentation.category.MainViewModel

interface RepositoryCategory {
    fun getCategories(callback: MainViewModel.Callback)
}