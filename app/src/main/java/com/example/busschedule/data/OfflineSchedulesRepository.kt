package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineSchedulesRepository(private val scheduleDao: ScheduleDao) : SchedulesRepository {
    override fun getAllSchedulesStream(): Flow<List<BusSchedule>> = scheduleDao.getAllSchedules()

    override fun getScheduleStream(id: Int): Flow<BusSchedule?> = scheduleDao.getSchedule(id)

    override suspend fun insertSchedule(item: BusSchedule) = scheduleDao.insert(item)

    override suspend fun deleteSchedule(item: BusSchedule) = scheduleDao.delete(item)

    override suspend fun updateSchedule(item: BusSchedule) = scheduleDao.update(item)
}
