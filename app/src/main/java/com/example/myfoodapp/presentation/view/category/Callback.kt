package com.example.myfoodapp.presentation.view.category

import com.example.myfoodapp.data.model.CategoriesDTO

interface Callback {
    fun onResponse(category: CategoriesDTO)
    fun onFail()
}