package com.bongbong.coupleapp.rootRib

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootRibScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootRibPresenter, RootRouter>() {

  @Inject
  lateinit var presenter: RootRibPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachLoggedIn()
    router.attachMenu()

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RootRibPresenter
}
