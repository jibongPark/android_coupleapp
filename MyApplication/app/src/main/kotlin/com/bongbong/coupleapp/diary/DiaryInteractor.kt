package com.bongbong.coupleapp.diary

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [DiaryScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class DiaryInteractor : Interactor<DiaryInteractor.DiaryPresenter, DiaryRouter>() {

  @Inject
  lateinit var presenter: DiaryPresenter

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
  interface DiaryPresenter
}
