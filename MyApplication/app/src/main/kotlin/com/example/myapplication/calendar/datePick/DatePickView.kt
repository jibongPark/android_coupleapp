package com.example.myapplication.calendar.datePick

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link DatePickBuilder.DatePickScope}.
 */
class DatePickView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle), DatePickInteractor.DatePickPresenter
