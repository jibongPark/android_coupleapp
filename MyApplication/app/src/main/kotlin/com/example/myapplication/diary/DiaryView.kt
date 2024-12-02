package com.example.myapplication.diary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Top level view for {@link DiaryBuilder.DiaryScope}.
 */
class DiaryView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), DiaryInteractor.DiaryPresenter {
    private val buttonClickSubject: PublishSubject<Unit> = PublishSubject.create()
    private var text = ""

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
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.Blue),
            ) {
                Text("top 10%")
            }

            Box(modifier = Modifier
                .weight(8f)
                .fillMaxWidth()
                .background(Color.White),
            ) {
                Text("middle 80%")
            }

            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Blue),
            ) {
                Text("bottom 80%")
            }
        }
    }
}
