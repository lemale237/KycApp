package com.example.kycapp.entites.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(@PrimaryKey var id:Int, var latitude:Double, var logitude:Double)
