package com.example.myapplication.rootRib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.example.myapplication.R

/**
 * Top level view for {@link RootRibBuilder.RootRibScope}.
 */
class RootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), RootInteractor.RootRibPresenter {

    fun addViewToMain(view: View) {
        findViewById<LinearLayout>(R.id.mainView)?.addView(view)
    }

    fun removeViewToMain(view:View) {
        findViewById<LinearLayout>(R.id.mainView)?.removeView(view)
    }

    fun addViewToMenu(view: View) {
        findViewById<FrameLayout>(R.id.menuView)?.addView(view)
    }

    fun removeViewToMenu(view: View) {
        findViewById<FrameLayout>(R.id.menuView)?.removeView(view)
    }

}