package com.bongbong.coupleapp.database

import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.Router

import javax.inject.Inject

/**
 * Coordinates Business Logic for [DatabaseScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class DatabaseInteractor : Interactor<EmptyPresenter, DatabaseRouter>() {

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }
}
