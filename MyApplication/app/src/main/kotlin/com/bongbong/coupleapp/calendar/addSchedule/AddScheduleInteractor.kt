package com.bongbong.coupleapp.calendar.addSchedule

import android.annotation.SuppressLint
import android.content.Context
import android.media.metrics.Event
import android.util.Log
import com.bongbong.coupleapp.util.EventStream
import com.bongbong.coupleapp.util.StateStream
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Coordinates Business Logic for [AddScheduleScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class AddScheduleInteractor : Interactor<AddScheduleInteractor.AddSchedulePresenter, AddScheduleRouter>() {

  @Inject
  lateinit var listener: Listener

  @Inject
  lateinit var presenter: AddSchedulePresenter

  @Inject
  lateinit var eventStream: EventStream<AddScheduleEvent>

  @Inject
  lateinit var stateStream: StateStream<AddScheduleViewModel>

  @SuppressLint("CheckResult")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .saveScheduleData()
      .subscribe {
        Log.d("TAG", "didBecomeActive: ")

        listener.removeAddSchedule()
      }

    eventStream
      .observe()
      .onEach {
        when (it) {
          is AddScheduleEvent.ScheduleNameChanged -> {
            with(stateStream) {
              dispatch(
                current()
                  .copy(
                    name = it.name
                  )
              )
            }
          }
        }
      }

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface AddSchedulePresenter {
    fun saveScheduleData(): Observable<Any?>
  }

  interface Listener {
    fun removeAddSchedule()
  }
}
