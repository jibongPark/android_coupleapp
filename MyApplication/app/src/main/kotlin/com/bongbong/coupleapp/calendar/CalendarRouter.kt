package com.bongbong.coupleapp.calendar

import android.view.View
import androidx.core.view.children
import com.bongbong.coupleapp.calendar.addSchedule.AddScheduleBuilder
import com.bongbong.coupleapp.calendar.addSchedule.AddScheduleRouter
import com.bongbong.coupleapp.calendar.schedule.ScheduleBuilder
import com.bongbong.coupleapp.calendar.schedule.ScheduleRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoggedOutBuilder.LoggedOutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CalendarRouter(
    view: CalendarView,
    interactor: CalendarInteractor,
    scheduleBuilder: ScheduleBuilder,
    addScheduleBuilder: AddScheduleBuilder,
    component: CalendarBuilder.Component
) : ViewRouter<CalendarView, CalendarInteractor>(view, interactor, component) {

    private var scheduleBuilder = scheduleBuilder
    private var scheduleRouter: ScheduleRouter? = null

    private var addScheduleBuilder = addScheduleBuilder
    private var addScheduleRouter: AddScheduleRouter? = null

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

    fun attachAddSchedule() {

        for(childView in view.children) {
            childView.visibility = View.GONE
        }

        addScheduleRouter = addScheduleBuilder.build(view)
        attachChild(addScheduleRouter!!)
        view.addView(addScheduleRouter!!.view)
    }

    fun detachAddSchedule() {
        if(addScheduleRouter != null) {
            detachChild(addScheduleRouter!!)
            view.removeView(addScheduleRouter!!.view)
        }
        addScheduleRouter = null

        for(childView in view.children) {
            childView.visibility = View.VISIBLE
        }
    }
}
