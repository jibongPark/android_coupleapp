package com.bongbong.coupleapp.loggedIn

import com.bongbong.coupleapp.calendar.CalendarBuilder
import com.bongbong.coupleapp.calendar.CalendarRouter
import com.bongbong.coupleapp.calendar.schedule.ScheduleBuilder
import com.bongbong.coupleapp.calendar.schedule.ScheduleRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedInRouter(
    view: LoggedInView,
    interactor: LoggedInInteractor,
    calendarBuilder: CalendarBuilder,
    scheduleBuilder: ScheduleBuilder,
    component: LoggedInBuilder.Component) : ViewRouter<LoggedInView, LoggedInInteractor>(view, interactor, component) {

    private var calendarBuilder = calendarBuilder
    private var scheduleBuilder = scheduleBuilder

    private var calendarRouter: CalendarRouter? = null
    private var scheduleRouter: ScheduleRouter? = null

    fun attachCalendar() {
        calendarRouter = calendarBuilder.build(view)
        attachChild(calendarRouter!!)
        view.addView(calendarRouter!!.view)
    }

    fun detachCalendar() {
        if(calendarRouter != null) {
            detachChild(calendarRouter!!)
            view.removeView(calendarRouter!!.view)
        }
        calendarRouter = null;
    }

    fun attachSchedule() {
        scheduleRouter = scheduleBuilder.build(view)
        attachChild(scheduleRouter!!)
        view.addView(scheduleRouter!!.view)
    }

    fun detachSchedule() {
        if(scheduleRouter != null) {
            detachChild(scheduleRouter!!)
            view.removeView(scheduleRouter!!.view)
        }
        scheduleRouter = null
    }
}
