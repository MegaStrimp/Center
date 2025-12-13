package com.example.center.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.center.data.TaskDatabase
import com.example.center.model.Task
import com.example.center.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository: TaskRepository

    init
    {
        val taskDao = TaskDatabase.Companion.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun readAllData(dateId: Int): LiveData<List<Task>>
    {
        return repository.readAllData(dateId)
    }

    fun addTask(task: Task)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteTask(task)
        }
    }

    fun deleteDatedTasks(dateId: Int)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteDatedTasks(dateId)
        }
    }

    fun deleteAllTasks(task: Task)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteAllTasks()
        }
    }
}