package com.bongbong.coupleapp.calendar.addSchedule

import java.time.LocalDate
import java.time.LocalTime

data class AddScheduleViewModel(
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val startTime: LocalTime = LocalTime.now(),
    val endTime: LocalTime = LocalTime.now(),
    val name: String = "",
    val content: String = ""
)