package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository provides insert, update, delete, and retrieve of [BusSchedule] from given data source.
 */
interface SchedulesRepository {
    /**
     * Retrieve all Schedules from given data source.
     */
    fun getAllSchedulesStream(): Flow<List<BusSchedule>>

    /**
     * Retrieve schedule from given data source that matches with [id].
     */
    fun getScheduleStream(id: Int): Flow<BusSchedule?>

    /**
     * Insert schedule into data source
     */
    suspend fun insertSchedule(item: BusSchedule)

    /**
     * Delete schedule from data source
     */
    suspend fun deleteSchedule(item: BusSchedule)

    /**
     * Update schedule in data source
     */
    suspend fun updateSchedule(item: BusSchedule)
}