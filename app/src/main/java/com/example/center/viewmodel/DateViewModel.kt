package com.example.center.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.center.data.DateDatabase
import com.example.center.repository.DateRepository
import com.example.center.model.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DateViewModel(application: Application): AndroidViewModel(application)
{
    val readAllData: LiveData<List<Date>>
    private val repository: DateRepository

    init
    {
        val dateDao = DateDatabase.Companion.getDatabase(application).dateDao()
        repository = DateRepository(dateDao)
        readAllData = repository.readAllData
    }

    fun addDate(date: Date)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addDate(date)
        }
    }

    fun updateDate(date: Date)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.updateDate(date)
        }
    }

    fun deleteDate(date: Date)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteDate(date)
        }
    }

    fun deleteAllDates(date: Date)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteAllDates()
        }
    }
}