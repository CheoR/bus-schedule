package com.example.busschedule.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents single [BusSchedule] database row.
 */
@Entity(tableName = "Schedule")
data class BusSchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val stopName: String,
    val arrivalTimeInMillis: Int
)
