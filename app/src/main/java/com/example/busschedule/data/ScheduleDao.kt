package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    // Run on separate thread.
    // Room doesn't allow database access on main thread.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: BusSchedule)

    @Update
    suspend fun update(item: BusSchedule)

    @Delete
    suspend fun delete(item: BusSchedule)

    @Query("SELECT * from Schedule WHERE id = :id")
    fun getSchedule(id: Int): Flow<BusSchedule>

    // Recommended to use Flow in persistence layer i.e. ItemDao.kt .
    // With Flow return type, receive notification whenever database data changes.
    // Room keeps Flow updated, only need to explicitly get data once. Helpful
    // for updating inventory list and similar things. Because Flow return type,
    // Room also runs query on background thread. No need to explicitly make it
    // suspend function and call it inside coroutine scope.
    @Query("SELECT * from Schedule ORDER BY arrivalTimeInMillis ASC")
    fun getAllSchedules(): Flow<List<BusSchedule>>

}