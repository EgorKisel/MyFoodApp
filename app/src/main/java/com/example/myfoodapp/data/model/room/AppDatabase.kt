package com.example.myfoodapp.data.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartItemDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()
        }
    }
}