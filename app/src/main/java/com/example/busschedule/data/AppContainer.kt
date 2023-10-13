package com.example.busschedule.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val schedulesRepository: SchedulesRepository
}

/**
 * [AppContainer] implementation provides [OfflineSchedulesRepository] instance
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * [schedulesRepository] implementation
     */
    override val schedulesRepository: SchedulesRepository by lazy {
        // Call getDatabase() to instantiate database instance
        // on InventoryDatabase class passing in context and call
        // .itemDao() to create Dao instance
        OfflineSchedulesRepository(ScheduleDatabase.getDatabase(context).scheduleDao())
    }
}
