package com.bongbong.coupleapp.calendar.addSchedule

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link AddScheduleBuilder.AddScheduleScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class AddScheduleRouter(
    view: AddScheduleView,
    interactor: AddScheduleInteractor,
    component: AddScheduleBuilder.Component) : ViewRouter<AddScheduleView, AddScheduleInteractor>(view, interactor, component) {
    override fun handleBackPress(): Boolean {

        return false
    }
}
