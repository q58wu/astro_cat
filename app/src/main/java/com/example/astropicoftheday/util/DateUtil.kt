package com.example.astropicoftheday.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object DateUtil {

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR, -(daysAgo*24))
        return calendar.time
    }

    fun getDaysAgoString(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR, -(daysAgo * 24))
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(calendar.time)
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getLastXDaysString(x: Int): List<String>{
        val daysList = mutableListOf<String>()
        for (i in 0..x-1){
            val date1 = getDaysAgo(i)
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val strDate: String = dateFormat.format(date1)
            daysList.add(strDate)
        }

        return daysList
    }
}