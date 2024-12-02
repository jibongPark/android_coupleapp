package com.example.myapplication.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

data class CalendarState @OptIn(ExperimentalFoundationApi::class) constructor(
    var currentYM: MutableState<YearMonth> = mutableStateOf(YearMonth.now()),
    var currentDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
) {

    var selectedDate: LocalDate = LocalDate.now()

    fun getDayOfMonth(yearMonth: YearMonth): List<LocalDate> {
        val startOfMonth = yearMonth.atDay(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val endOfMonth = yearMonth.atEndOfMonth().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
        return generateSequence(startOfMonth) {it.plusDays(1) }
            .takeWhile { !it.isAfter(endOfMonth) }
            .toList()
    }

    companion object {
        val Saver: Saver<CalendarState, Any> = listSaver(
            save = {
                listOf(
                    it.currentYM.value.toString(), it.currentDate.value.toString()
                )
            },
            restore = { savedValue ->
                CalendarState(
                    currentYM = mutableStateOf(YearMonth.parse(savedValue[0])),
                    currentDate = mutableStateOf(LocalDate.parse(savedValue[1]))
                )
            }
        )
    }
}

@Composable
fun rememberCalendarState(
    initialYM: YearMonth = YearMonth.now(),
    initialDate: LocalDate = LocalDate.now()
): CalendarState {
    return rememberSaveable(
        saver = CalendarState.Saver
    ){
        CalendarState(
            currentYM = mutableStateOf(initialYM),
            currentDate = mutableStateOf(initialDate)
        )
    }

}