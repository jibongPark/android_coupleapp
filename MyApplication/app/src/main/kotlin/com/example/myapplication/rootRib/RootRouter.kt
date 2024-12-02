package com.example.myapplication.rootRib

import com.example.myapplication.calendar.CalendarBuilder
import com.example.myapplication.calendar.CalendarRouter
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
    menuBuilder: MenuBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    private var loggedOutBuilder = calendarBuilder
    private var menuBuilder = menuBuilder

    private var calendarRouter: CalendarRouter? = null
    private var menuRouter: MenuRouter? = null


    fun attachCalendar() {
        calendarRouter = loggedOutBuilder.build(view)
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



