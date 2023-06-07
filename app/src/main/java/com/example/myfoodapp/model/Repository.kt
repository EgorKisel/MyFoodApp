package com.example.myfoodapp.model

import com.example.myfoodapp.viewmodel.MainViewModel

interface Repository {
    fun getCategories(callback: MainViewModel.Callback)
}