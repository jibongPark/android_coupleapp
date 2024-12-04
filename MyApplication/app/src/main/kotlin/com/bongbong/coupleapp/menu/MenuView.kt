package com.bongbong.coupleapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView

/**
 * Top level view for {@link MenuBuilder.MenuScope}.
 */
class MenuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), MenuInteractor.MenuPresenter {
    private val composeView = ComposeView(context).apply {
        setContent {
            MaterialTheme {
                drawView()
            }
        }
    }

    init {
        addView(composeView)
    }

    @Composable
    fun drawView() {
        Row {
            Button(
                onClick = {}
            ) {
                Text(text = "test Button")
            }
            Button(
                onClick = {}
            ) {
                Text(text = "test Button2")
            }
            Button(
                onClick = {}
            ) {
                Text(text = "test Button3")
            }
        }
    }
}
