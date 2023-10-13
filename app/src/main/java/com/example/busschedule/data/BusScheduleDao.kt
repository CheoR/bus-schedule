package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Provides schedule table read/write operations.
 * Used by view models to format query results for use in UI.
 */
@Dao
interface BusScheduleDao {
    // Run on separate thread.
    // Room doesn't allow database access on main thread.
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(item: BusSchedule)
//
//    @Update
//    suspend fun update(item: BusSchedule)
//
//    @Delete
//    suspend fun delete(item: BusSchedule)
//
//    @Query("SELECT * from Schedule WHERE id = :id")
//    fun getSchedule(id: Int): Flow<BusSchedule>

    // Recommended to use Flow in persistence layer i.e. ItemDao.kt .
    // With Flow return type, receive notification whenever database data changes.
    // Room keeps Flow updated, only need to explicitly get data once. Helpful
    // for updating inventory list and similar things. Because Flow return type,
    // Room also runs query on background thread. No need to explicitly make it
    // suspend function and call it inside coroutine scope.
//    @Query("SELECT * from Schedule ORDER BY arrivalTimeInMillis ASC")
//    fun getAllSchedules(): Flow<List<BusSchedule>>
    @Query(
        """
        SELECT * FROM schedule 
        ORDER BY arrival_time ASC    
        """
    )
    fun getAll(): Flow<List<BusSchedule>>

    @Query(
        """
        SELECT * FROM schedule 
        WHERE stop_name = :stopName 
        ORDER BY arrival_time ASC 
        """
    )
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>

}