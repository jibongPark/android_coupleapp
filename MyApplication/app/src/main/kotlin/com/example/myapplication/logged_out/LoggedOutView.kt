package com.example.myapplication.logged_out

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.w3c.dom.Attr

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */


class LoggedOutView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attr, defStyleAttr), LoggedOutInteractor.LoggedOutPresenter {

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

        var playerOneName by remember { mutableStateOf("") }
        var playerTwoName by remember { mutableStateOf("") }


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

    override fun loginName(): Observable<Any>? {
        return buttonClickSubject.map(){
            text
        }
    }
}
