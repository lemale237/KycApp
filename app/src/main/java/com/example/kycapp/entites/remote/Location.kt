package com.example.kycapp.entites.remote

import java.util.*

data class Location(var latitude:Double,var longitude: Double,var date: Date?){
    constructor() : this(0.0,0.0,Date())
}