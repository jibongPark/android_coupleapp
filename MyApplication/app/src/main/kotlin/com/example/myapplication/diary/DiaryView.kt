package com.example.myapplication.diary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * Top level view for {@link DiaryBuilder.DiaryScope}.
 */
class DiaryView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), DiaryInteractor.DiaryPresenter
