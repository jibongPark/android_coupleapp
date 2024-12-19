package com.bongbong.coupleapp.calendar.addSchedule

sealed class AddScheduleEvent {
    class ScheduleNameChanged(val name: String): AddScheduleEvent()
}