package com.bongbong.coupleapp.calendar

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import java.time.LocalDate

class MutableDateStream : DateStream {

    val dateRelay : BehaviorRelay<LocalDate> = BehaviorRelay.create()

    fun selectDate(date: LocalDate) {
        dateRelay.accept(date)
    }

    override fun date() : Observable<LocalDate> {
        return dateRelay.hide()
    }
}