package com.example.myfoodapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfoodapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, FragmentMain.newInstance()).commit()
        }
    }
}