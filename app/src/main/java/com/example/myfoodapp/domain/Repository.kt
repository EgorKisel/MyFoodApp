package com.example.myfoodapp.domain

import com.example.myfoodapp.presentation.view.category.MainViewModel

interface Repository {
    fun getCategories(callback: MainViewModel.Callback)
}