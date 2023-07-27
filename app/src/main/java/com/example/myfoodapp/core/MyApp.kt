package com.example.myfoodapp.core

import android.app.Application
import androidx.room.Room
import com.example.myfoodapp.data.model.room.AppDatabase
import com.example.myfoodapp.data.model.room.CartItemDao
import com.example.myfoodapp.di.AppComponent
import com.example.myfoodapp.di.AppModule
import com.example.myfoodapp.di.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone


class MyApp: Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()


    companion object {
        lateinit var appInstance: MyApp
        private var database: AppDatabase? = null
        private var DB_NAME = "myapp_database"

        fun getBasketItems(): CartItemDao {
            if (database == null) {
                synchronized(AppDatabase::class.java) {
                    if (database == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null")
                        database = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            AppDatabase::class.java,
                            DB_NAME
                        ).build()
                    }
                }
            }
            return database!!.cartItemDao()
        }
    }


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}