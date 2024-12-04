package com.example.myapplication.calendar.schedule

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView

/**
 * Top level view for {@link ScheduleBuilder.ScheduleScope}.
 */
class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), ScheduleInteractor.SchedulePresenter {
    private val composeView = ComposeView(context).apply {
        setContent {
            MaterialTheme {
                var message by remember { mutableStateOf("init message") }
                drawView(message)
            }
        }
    }

    init {
        addView(composeView)
    }

    @Composable
    fun drawView(message: String) {

        Column(modifier = Modifier.fillMaxSize()) {


            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            ) {
                Text("schedule View")
            }

        }
    }
}
