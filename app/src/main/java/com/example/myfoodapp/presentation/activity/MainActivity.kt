package com.example.myfoodapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfoodapp.R
import com.example.myfoodapp.core.MyApp
import com.example.myfoodapp.databinding.ActivityMainBinding
import com.example.myfoodapp.presentation.BackPressedListener
import com.example.myfoodapp.presentation.basket.FragmentBasket
import com.example.myfoodapp.presentation.category.FragmentCategory
import com.example.myfoodapp.test.Creation
import com.example.myfoodapp.test.Operators
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.fragmentContainerMain)
    private val viewModel = MainViewModel(Router())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creation().exec()
        Operators().exec()

        disableUnusedMenus()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, FragmentCategory.newInstance()).commit()
        }
        init()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        MyApp.appInstance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        MyApp.appInstance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach {fragment ->
            if (fragment is BackPressedListener && fragment.onBackPressed()) {
                return
            }
        }
        viewModel.onBackPressed()
    }

    private fun disableUnusedMenus() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val menuItemActionSearchScreen = bottomNavigationView.menu.findItem(R.id.actionSearchScreen)
        val menuItemActionBasketScreen = bottomNavigationView.menu.findItem(R.id.actionBasketScreen)
        val menuItemActionAccountScreen =
            bottomNavigationView.menu.findItem(R.id.actionAccountScreen)

        menuItemActionSearchScreen.isEnabled = false
        menuItemActionAccountScreen.isEnabled = false
    }

    private fun init() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionMainScreen -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerMain, FragmentCategory.newInstance())
                        .commit()
                }

                R.id.actionBasketScreen -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerMain, FragmentBasket.newInstance())
                        .addToBackStack("").commit()
                }

                R.id.actionAccountScreen -> {}
                R.id.actionSearchScreen -> {}
            }
            true
        }
    }
}