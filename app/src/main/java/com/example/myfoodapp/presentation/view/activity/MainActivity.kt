package com.example.myfoodapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfoodapp.R
import com.example.myfoodapp.presentation.view.category.FragmentCategory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO сделать как в фрагменте
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, FragmentCategory.newInstance()).commit()
        }
    }
}