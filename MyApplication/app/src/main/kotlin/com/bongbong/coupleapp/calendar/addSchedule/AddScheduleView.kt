package com.bongbong.coupleapp.calendar.addSchedule

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.bongbong.coupleapp.calendar.CalendarBuilder
import com.bongbong.coupleapp.util.EventStream
import com.bongbong.coupleapp.util.StateStream
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.time.LocalDate
import javax.inject.Inject

/**
 * Top level view for {@link AddScheduleBuilder.AddScheduleScope}.
 */
class AddScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), AddScheduleInteractor.AddSchedulePresenter {
    private val composeView = ComposeView(context).apply {
        setContent {
            MaterialTheme {
                drawView()
            }
        }
    }

    private val addScheduleSubject: PublishSubject<Unit> = PublishSubject.create()

    @Inject
    lateinit var stateStream: StateStream<AddScheduleViewModel>

    @Inject
    lateinit var eventStream: EventStream<AddScheduleEvent>

    init {
        addView(composeView)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun drawView() {

//        val viewModel = stateStream.observe().collectAsState(initial = stateStream.current())

        var startDate = remember { mutableStateOf("") }

        var startDate2 = rememberDatePickerState(
            yearRange = 1999..2999,
            initialDisplayMode = DisplayMode.Picker
        )

        var dateRangeState = rememberDateRangePickerState(
            yearRange = 1999..2999,
            initialDisplayMode = DisplayMode.Picker
        )

        Column (
            modifier = Modifier.fillMaxSize()
        ) {

            TextField(
                value = "ㅅㄷㄴㅅ",
                onValueChange = { eventStream.notify(AddScheduleEvent.ScheduleNameChanged(it)) },

            )
            Row {
                Button(
                    onClick = {

                    }
                ) {
                    Text(text = "12월 11일(수)")
                }
            }

            Row {
                Button(
                    onClick = {
                        addScheduleSubject.onNext(Unit)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "save")
                }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                ) {
                    DateRangePicker(state = dateRangeState)
                }
            }
        }
    }

    override fun saveScheduleData(): Observable<Any?> {
        return addScheduleSubject.map() {

        }
    }


}
