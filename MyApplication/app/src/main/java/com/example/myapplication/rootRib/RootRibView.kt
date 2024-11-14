package com.example.myapplication.rootRib

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link RootRibBuilder.RootRibScope}.
 */
class RootRibView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle), RootInteractor.RootRibPresenter
