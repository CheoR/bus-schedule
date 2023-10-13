package com.example.busschedule.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.SchedulesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve all Room database items.
 */

class BusScheduleViewModel(schedulesRespoistory: SchedulesRepository): ViewModel() {
    // retrieve all items in Room database as StateFlow observable API for UI state.
    // When Room Inventory data changes, UI updates automatically.
    /**
     * Holds home ui state. The list of items are retrieved from [SchedulesRepository] and mapped to
     * [HomeUiState]
     */
    val homeUiState: StateFlow<HomeUiState> =
        // constantly updadating state
        schedulesRespoistory.getAllSchedulesStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    // Get example bus schedule
    fun getFullSchedule(): Flow<List<BusSchedule>> = flowOf(
        listOf(
            BusSchedule(
                1,
                "Example Street",
                0
            )
        )
    )

    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = flowOf(
        listOf(
            BusSchedule(
                1,
                "Example Street",
                0
            )
        )
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                BusScheduleViewModel(
                    // To create new SavedStateHandle instance
                    // A SavedStateHandle handle to saved state passed down to
                    // androidx.lifecycle.ViewModel. Use SavedStateViewModelFactory to receive this
                    // object in ViewModel's constructor.
                    // This is key-value map that lets you write and retrieve objects to and from saved
                    // state. These values persist after system kills process and remain available via same object.
//                    this.createSavedStateHandle(),
                    busScheduleApplication().container.schedulesRepository
                )
            }
        }
    }
}

/**
 * HomeScreen Ui State
 */
data class HomeUiState(val scheduleList: List<BusSchedule> = listOf())

/**
 * Extension function queries for [Application] object and returns [BusScheduleApplication] instance.
 */
fun CreationExtras.busScheduleApplication(): BusScheduleApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)
