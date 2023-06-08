package com.example.myfoodapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfoodapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disableUnusedMenus()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, FragmentCategory.newInstance()).commit()
        }
    }

    private fun disableUnusedMenus() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val menuItemActionSearchScreen = bottomNavigationView.menu.findItem(R.id.actionSearchScreen)
        val menuItemActionBasketScreen = bottomNavigationView.menu.findItem(R.id.actionBasketScreen)
        val menuItemActionAccountScreen =
            bottomNavigationView.menu.findItem(R.id.actionAccountScreen)

        menuItemActionSearchScreen.isEnabled = false
        menuItemActionBasketScreen.isEnabled = false
        menuItemActionAccountScreen.isEnabled = false
    }
}