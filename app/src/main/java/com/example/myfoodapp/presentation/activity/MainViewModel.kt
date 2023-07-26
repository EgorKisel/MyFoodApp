package com.example.myfoodapp.presentation.activity

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(private val router: Router) : ViewModel() {

    fun onBackPressed() {
        router.exit()
    }
}