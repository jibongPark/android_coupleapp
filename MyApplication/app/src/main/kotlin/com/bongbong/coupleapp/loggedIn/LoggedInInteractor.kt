package com.bongbong.coupleapp.loggedIn

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedInScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedInInteractor : Interactor<LoggedInInteractor.LoggedInPresenter, LoggedInRouter>() {

  @Inject
  lateinit var presenter: LoggedInPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachCalendar()
    router.attachSchedule()
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
