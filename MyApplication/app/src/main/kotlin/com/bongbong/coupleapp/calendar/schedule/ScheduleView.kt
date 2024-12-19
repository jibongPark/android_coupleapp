package com.bongbong.coupleapp.calendar.schedule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.time.LocalDate

/**
 * Top level view for {@link ScheduleBuilder.ScheduleScope}.
 */
class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle),
    ScheduleInteractor.SchedulePresenter {
    private val composeView = ComposeView(context).apply {
        setContent {
            MaterialTheme {
                drawView()
            }
        }
    }

    private val schedules =  mutableStateOf (ArrayList<ScheduleData>())

    private val addScheduleSubject: PublishSubject<LocalDate> = PublishSubject.create()

    init {
        addView(composeView)
    }

    @Composable
    fun drawView() {

        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            ) {
                Text(schedules.value.size.toString() + " 개")

                Button(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(0.dp, 0.dp, 10.dp, 10.dp),
                    onClick = {
                        addScheduleSubject.onNext(LocalDate.now())
                }) {
                    Text(text = "+")
                }
            }

        }
    }

    override fun showSchedule(schedules: ArrayList<ScheduleData>) {
        this.schedules.value.clear()
        this.schedules.value.addAll(schedules)
    }

    override fun addSchedule(): Observable<Any?> {
        return addScheduleSubject.map() {

        }
    }
}
