package com.example.myfoodapp.data.repoimpl

import com.example.myfoodapp.data.model.network.CategoriesDTO
import com.example.myfoodapp.data.service.CategoriesApi
import com.example.myfoodapp.domain.RepositoryCategory
import io.reactivex.rxjava3.core.Single

class RepositoryCategoryImpl(private val categoriesApi: CategoriesApi) : RepositoryCategory {

    override fun getCategories(): Single<List<CategoriesDTO>> {
        return categoriesApi.getCategories()
    }
}