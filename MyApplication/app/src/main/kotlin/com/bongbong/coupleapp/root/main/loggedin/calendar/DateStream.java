package com.bongbong.coupleapp.root.main.loggedin.calendar;

import io.reactivex.Observable;

import java.time.LocalDate;

public interface DateStream {
    Observable<LocalDate> date();
}
