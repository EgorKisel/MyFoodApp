package com.example.myfoodapp.presentation.activity

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

class MainViewModel(private val router: Router): ViewModel() {

    fun onBackPressed() {
        router.exit()
    }
}