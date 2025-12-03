package com.example.center.repository

import androidx.lifecycle.LiveData
import com.example.center.data.DateDao
import com.example.center.model.Date

class DateRepository(private val dateDao: DateDao)
{
    val readAllData: LiveData<List<Date>> = dateDao.readAllData()

    suspend fun addDate(date: Date)
    {
        dateDao.addDate(date)
    }

    suspend fun updateDate(date: Date)
    {
        dateDao.updateDate(date)
    }

    suspend fun deleteDate(date: Date)
    {
        dateDao.deleteDate(date)
    }

    suspend fun deleteAllDates()
    {
        dateDao.deleteAllDates()
    }
}