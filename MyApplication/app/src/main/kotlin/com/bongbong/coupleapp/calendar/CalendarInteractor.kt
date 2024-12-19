package com.bongbong.coupleapp.calendar

import android.annotation.SuppressLint
import android.util.Log
import com.bongbong.coupleapp.calendar.addSchedule.AddScheduleInteractor
import com.bongbong.coupleapp.calendar.schedule.ScheduleInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import java.time.LocalDate

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

  @Inject
  @CalendarBuilder.CalendarInternal
  lateinit var dateStream: MutableDateStream

  @SuppressLint("CheckResult")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .dayClick()
        ?.subscribe { selectedDate ->
          dateStream.selectDate(selectedDate as LocalDate)
        }

    router.attachSchedule()
  }

  inner class ScheduleListener : ScheduleInteractor.Listener {
    override fun showAddSchedule(date: LocalDate) {
      router.attachAddSchedule()
    }
  }

  inner class AddScheduleListner: AddScheduleInteractor.Listener {
    override fun removeAddSchedule() {
      router.detachAddSchedule()
    }
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
}
