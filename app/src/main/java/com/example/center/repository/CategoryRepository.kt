package com.example.center.repository

import androidx.lifecycle.LiveData
import com.example.center.data.CategoryDao
import com.example.center.model.Category

class CategoryRepository(private val categoryDao: CategoryDao)
{
    val readAllData: LiveData<List<Category>> = categoryDao.readAllData()

    suspend fun addCategory(category: Category)
    {
        categoryDao.addCategory(category)
    }

    suspend fun updateCategory(category: Category)
    {
        categoryDao.updateCategory(category)
    }

    suspend fun deleteCategory(category: Category)
    {
        categoryDao.deleteCategory(category)
    }

    suspend fun deleteAllCategories()
    {
        categoryDao.deleteAllCategories()
    }
}