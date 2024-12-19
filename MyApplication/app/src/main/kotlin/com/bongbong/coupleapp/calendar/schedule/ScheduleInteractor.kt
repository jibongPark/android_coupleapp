package com.bongbong.coupleapp.calendar.schedule

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.bongbong.coupleapp.calendar.CalendarInteractor
import com.bongbong.coupleapp.calendar.DateStream
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.log

/**
 * Coordinates Business Logic for [ScheduleScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class ScheduleInteractor() : Interactor<ScheduleInteractor.SchedulePresenter, ScheduleRouter>() {

  @Inject
  lateinit var presenter: SchedulePresenter

  @Inject
  lateinit var listener: Listener

  @Inject
  lateinit var dateStream: DateStream

  private var selectedDate: LocalDate = LocalDate.now()

  @SuppressLint("CheckResult")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    dateStream
      .date()
      ?.subscribe { date ->
        selectedDate = date
//        presenter.showSchedule()
      }


    presenter.addSchedule()
        .subscribe {
          listener.showAddSchedule(selectedDate)
        }

  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SchedulePresenter {
    fun showSchedule(schedules: ArrayList<ScheduleData>)

    fun addSchedule(): Observable<Any?>
  }

  interface Listener {
    fun showAddSchedule(date: LocalDate)
  }

}
