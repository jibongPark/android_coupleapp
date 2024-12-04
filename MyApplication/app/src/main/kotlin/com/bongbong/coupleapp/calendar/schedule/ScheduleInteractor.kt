package com.bongbong.coupleapp.calendar.schedule

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ScheduleScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class ScheduleInteractor : Interactor<ScheduleInteractor.SchedulePresenter, ScheduleRouter>() {

  @Inject
  lateinit var presenter: SchedulePresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SchedulePresenter
}
