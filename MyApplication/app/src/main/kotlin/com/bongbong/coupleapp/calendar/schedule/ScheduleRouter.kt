package com.bongbong.coupleapp.calendar.schedule

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ScheduleBuilder.ScheduleScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ScheduleRouter(
    view: ScheduleView,
    interactor: ScheduleInteractor,
    component: ScheduleBuilder.Component
) : ViewRouter<ScheduleView, ScheduleInteractor>(view, interactor, component)
