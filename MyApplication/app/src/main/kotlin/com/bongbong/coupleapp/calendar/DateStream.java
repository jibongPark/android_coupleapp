package com.bongbong.coupleapp.calendar;

import io.reactivex.Observable;

import java.time.LocalDate;

public interface DateStream {
    Observable<LocalDate> date();
}
