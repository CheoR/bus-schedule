package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with singleton Instance object.
 */

// version - increase version number whenever database table schema changes.
// exportSchema - set as false as to not keep schema version history backup
@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    // allows access to methods to create or get database and use class name as
    // qualifier
    companion object {
        // Instance - keeps reference to database, when one has been created.
        // This helps maintain single database instance opened at given time,
        // which is expensive resource to create and maintain.
        // volatile - value never cached, and all reads and writes are to and
        // from main memory. These features help ensure Instance value is always
        // up to date and is same for all execution threads. It means that
        // changes made by one thread to Instance are immediately visible to all
        // other threads.
        @Volatile
        private var Instance: ScheduleDatabase? = null

        fun getDatabase(context: Context): ScheduleDatabase {
            // if the Instance is not null, return it, otherwise create a new
            // database instance.
            // synchronized block means that only one thread of execution at
            // time can enter this block of code, which makes sure database only
            // gets initialized once.
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, ScheduleDatabase::class.java, "schedule_database")
                    /**
                     * Setting option in app's database builder means that Room
                     * permanently deletes all data from database tables when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    // required migration strategy
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
