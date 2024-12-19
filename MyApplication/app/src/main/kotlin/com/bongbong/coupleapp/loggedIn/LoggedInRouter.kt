package com.bongbong.coupleapp.loggedIn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.ComposeView
import com.bongbong.coupleapp.calendar.CalendarBuilder
import com.bongbong.coupleapp.calendar.CalendarRouter
import com.bongbong.coupleapp.calendar.schedule.ScheduleBuilder
import com.bongbong.coupleapp.calendar.schedule.ScheduleRouter
import com.uber.rib.core.BasicComposeRouter
import com.uber.rib.core.ComposePresenter

import com.uber.rib.core.ViewRouter
import java.time.LocalDate

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedInRouter(
    presenter: ComposePresenter,
    interactor: LoggedInInteractor,
    calendarBuilder: CalendarBuilder,
    slot: MutableState<@Composable () -> Unit>
) : BasicComposeRouter<LoggedInInteractor>(presenter, interactor, slot) {

    private var calendarBuilder = calendarBuilder

    private var calendarRouter: CalendarRouter? = null

    fun attachCalendar() {

        calendarRouter = calendarBuilder.build()
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

    fun onDateChanged(date: LocalDate) {
        
    }
}
