package com.example.myfoodapp

import android.app.Application
import androidx.room.Room
import com.example.myfoodapp.data.model.room.AppDatabase
import com.example.myfoodapp.data.model.room.CartItemDao
import java.lang.IllegalStateException


class MyApp: Application() {
    companion object {
        private var appInstance: MyApp? = null
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



    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}