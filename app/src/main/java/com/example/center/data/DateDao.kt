package com.example.center.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.center.model.Date

@Dao
interface DateDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDate(date: Date)

    @Update
    suspend fun updateDate(date: Date)

    @Delete
    suspend fun deleteDate(date: Date)

    @Query("DELETE FROM date_table")
    suspend fun deleteAllDates()

    @Query("SELECT * FROM date_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Date>>
}