package com.example.myapplication.rootRib

import com.example.myapplication.calendar.CalendarBuilder
import com.example.myapplication.calendar.CalendarRouter
import com.example.myapplication.calendar.schedule.ScheduleBuilder
import com.example.myapplication.calendar.schedule.ScheduleRouter
import com.example.myapplication.loggedIn.LoggedInBuilder
import com.example.myapplication.menu.MenuBuilder
import com.example.myapplication.menu.MenuRouter
import com.uber.rib.core.ViewRouter
import com.example.myapplication.rootRib.RootBuilder.Component


/**
 * Adds and removes children of {@link RootRibBuilder.RootRibScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: Component,
    calendarBuilder: CalendarBuilder,
    scheduleBuilder: ScheduleBuilder,
    menuBuilder: MenuBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    private var calendarBuilder = calendarBuilder
    private var menuBuilder = menuBuilder
    private var scheduleBuilder = scheduleBuilder

    private var calendarRouter: CalendarRouter? = null
    private var menuRouter: MenuRouter? = null
    private var scheduleRouter: ScheduleRouter? = null


    fun attachCalendar() {
        calendarRouter = calendarBuilder.build(view)
        attachChild(calendarRouter!!)
        view.addViewToMain(calendarRouter!!.view)
    }

    fun detachCalendar() {
        if(calendarRouter != null) {
            detachChild(calendarRouter!!)
            view.removeViewToMain(calendarRouter!!.view)
        }
        calendarRouter = null;
    }

    fun attachSchedule() {
        scheduleRouter = scheduleBuilder.build(view)
        attachChild(scheduleRouter!!)
        view.addViewToMain(scheduleRouter!!.view)
    }

    fun attachMenu() {
        menuRouter = menuBuilder.build(view)
        attachChild(menuRouter!!)
        view.addViewToMenu(menuRouter!!.view)
    }

    fun detachMenu() {
        if(menuRouter != null) {
            detachChild(menuRouter!!)
            view.removeViewToMenu(menuRouter!!.view)
        }

        menuRouter = null;
    }
}



