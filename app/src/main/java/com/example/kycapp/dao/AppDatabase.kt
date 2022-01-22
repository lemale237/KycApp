package com.example.kycapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kycapp.dao.LocationDao.LocationDao
import com.example.kycapp.entites.local.Location

@Database(entities = [Location::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): LocationDao
}