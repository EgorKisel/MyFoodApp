package com.example.myfoodapp.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartItemDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
}