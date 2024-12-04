package com.example.myapplication.loggedIn

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * Top level view for {@link LoggedInBuilder.LoggedInScope}.
 */
class LoggedInView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), LoggedInInteractor.LoggedInPresenter {

}
