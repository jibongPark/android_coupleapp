package com.example.myapplication.calendar

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoggedOutBuilder.LoggedOutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CalendarRouter(
    view: CalendarView,
    interactor: CalendarInteractor,
    component: CalendarBuilder.Component) : ViewRouter<CalendarView, CalendarInteractor>(view, interactor, component)
