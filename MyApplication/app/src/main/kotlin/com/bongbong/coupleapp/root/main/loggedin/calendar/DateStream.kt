package com.bongbong.coupleapp.root.main.loggedin.calendar

import com.uber.rib.core.RibDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DateStream(date: LocalDate = LocalDate.now()) {
    private val _dateFlow = MutableStateFlow<LocalDate?>(date)

    private val dateFLow = _dateFlow.asStateFlow()

    suspend fun setDate(newDate: LocalDate?) {
        withContext(RibDispatchers.Default) {
            _dateFlow.update {
                newDate
            }
        }
    }

    fun date() = dateFLow
}