package com.bongbong.coupleapp.calendar.schedule

import java.time.LocalDate
import java.time.LocalTime

class ScheduleData(
    val uid: Int = 0,
    var startDate: LocalDate,
    var endDate: LocalDate,

    var startTime: LocalTime,
    var endTime: LocalTime,

    var name: String,
    var content: String
)