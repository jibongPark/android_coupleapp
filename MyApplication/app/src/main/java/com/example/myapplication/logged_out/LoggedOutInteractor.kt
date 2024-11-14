package com.example.myapplication.logged_out

import android.annotation.SuppressLint
import android.util.Log
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.functions.Consumer

import javax.inject.Inject


/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedOutInteractor : Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter>() {

  @Inject
  lateinit var presenter: LoggedOutPresenter

  @SuppressLint("CheckResult")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    presenter
      .loginName()
        ?.subscribe(Consumer<Any> { name -> Log.d("MOO", name.toString()) })
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface LoggedOutPresenter {
    fun loginName(): Observable<Any>?
  }
}
