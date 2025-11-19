package com.example.center.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "date_table")
data class Date(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
): Parcelable