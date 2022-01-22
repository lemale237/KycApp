package com.example.kycapp.dao.LocationDao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kycapp.entites.local.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    fun getAll():List<Location>

    @Query("SELECT * FROM location WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Location>

    @Query("SELECT * FROM location WHERE latitude LIKE :first AND " +
            "logitude LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Location

    @Insert
    fun insertAll(vararg users: Location)

    @Insert
    fun insert( user: Location)

    @Delete
    fun delete(user: Location)
}