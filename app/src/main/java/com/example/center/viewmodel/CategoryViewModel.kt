package com.example.center.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.center.data.CategoryDatabase
import com.example.center.repository.CategoryRepository
import com.example.center.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application): AndroidViewModel(application)
{
    val readAllData: LiveData<List<Category>>
    private val repository: CategoryRepository

    init
    {
        val categoryDao = CategoryDatabase.Companion.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        readAllData = repository.readAllData
    }

    fun addCategory(category: Category)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addCategory(category)
        }
    }

    fun updateCategory(category: Category)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteCategory(category)
        }
    }

    fun deleteAllCategories(category: Category)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteAllCategories()
        }
    }
}