package com.example.center.repository

import androidx.lifecycle.LiveData
import com.example.center.data.TaskDao
import com.example.center.model.Task

class TaskRepository(private val taskDao: TaskDao)
{
    suspend fun addTask(task: Task)
    {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task)
    {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task)
    {
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTasks()
    {
        taskDao.deleteAllTasks()
    }

    suspend fun deleteDatedTasks(date: Int)
    {
        return taskDao.deleteDatedTasks(date)
    }

    fun readAllData(date: Int): LiveData<List<Task>>
    {
        return taskDao.readAllData(date)
    }

}