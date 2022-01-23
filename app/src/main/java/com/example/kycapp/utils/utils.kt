package com.example.kycapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object utils {
    /**
     * Date formater
     *
     * @param date
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormater(date: Date):String{
        val localDateTime: LocalDateTime = LocalDateTime.parse(date.toString())
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val output: String = formatter.format(localDateTime)
        return output

    }
}