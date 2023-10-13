package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.AppDatabase

class BusScheduleApplication : Application() {

    /**
     * AppContainer instance used by rest of classes to obtain dependencies
     */

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
