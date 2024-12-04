package com.example.myapplication.calendar

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
class CalendarInteractor : Interactor<CalendarInteractor.CalendarPresenter, CalendarRouter>() {

  @Inject
  lateinit var presenter: CalendarPresenter

  @SuppressLint("CheckResult")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .dayClick()
        ?.subscribe { selectedDate -> Log.d("MOO", selectedDate.toString()) }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CalendarPresenter {
    fun dayClick(): Observable<Any>?
  }

  interface Listner {
    fun login(userName: String)
  }
}
