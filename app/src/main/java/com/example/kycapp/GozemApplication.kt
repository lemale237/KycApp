package com.example.kycapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kycapp.dao.AppDatabase

class GozemApplication : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, AppDatabase::class.java, "gozem_location").build()
    }
}