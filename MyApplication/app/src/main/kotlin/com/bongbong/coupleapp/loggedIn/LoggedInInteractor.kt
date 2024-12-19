package com.bongbong.coupleapp.loggedIn

import com.bongbong.coupleapp.calendar.MutableDateStream
import com.bongbong.coupleapp.calendar.schedule.ScheduleInteractor
import com.uber.rib.core.*
import java.time.LocalDate
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedInScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedInInteractor(
  presenter: ComposePresenter
) : BasicInteractor<LoggedInInteractor.LoggedInPresenter, LoggedInRouter>(presenter) {


  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachCalendar()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface LoggedInPresenter
}
