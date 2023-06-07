package com.example.myfoodapp.viewmodel

import com.example.myfoodapp.response.categories.CategoriesDTO

sealed class AppState {
    object Loading: AppState()
    data class Success(val categoryDTO: CategoriesDTO): AppState()
    data class Error(val error: Throwable): AppState()
}