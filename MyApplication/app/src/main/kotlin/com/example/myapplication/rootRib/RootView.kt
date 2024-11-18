package com.example.myapplication.rootRib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Top level view for {@link RootRibBuilder.RootRibScope}.
 */
class RootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle), RootInteractor.RootRibPresenter {}