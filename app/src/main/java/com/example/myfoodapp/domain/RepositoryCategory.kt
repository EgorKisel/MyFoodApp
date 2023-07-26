package com.example.myfoodapp.domain

import com.example.myfoodapp.data.model.network.CategoriesDTO
import io.reactivex.rxjava3.core.Single

interface RepositoryCategory {
    fun getCategories():Single<List<CategoriesDTO>>
}