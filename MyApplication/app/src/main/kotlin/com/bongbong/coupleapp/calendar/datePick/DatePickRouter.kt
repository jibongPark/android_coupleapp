package com.bongbong.coupleapp.calendar.datePick

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link DatePickBuilder.DatePickScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class DatePickRouter(
    view: DatePickView,
    interactor: DatePickInteractor,
    component: DatePickBuilder.Component) : ViewRouter<DatePickView, DatePickInteractor>(view, interactor, component)
