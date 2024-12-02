package com.example.myapplication.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */


class CalendarView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attr, defStyleAttr), CalendarInteractor.CalendarPresenter {

    private val buttonClickSubject: PublishSubject<Unit> = PublishSubject.create()
    private var text = ""

    private val composeView = ComposeView(context).apply {
        setContent {
            MaterialTheme {
//                rememberSaveable(saver = CalendarState.Saver) {CalendarState() }
                var state = rememberCalendarState()

                drawView(state)
            }
        }
    }

    init {
        addView(composeView)
    }

    @Composable
    fun drawView(state: CalendarState) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.Blue),
            ) {
                Calendar(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight(),
                    state = state,
                    onClick = {

                    }
                )
            }
        }
    }

    override fun loginName(): Observable<Any>? {
        return buttonClickSubject.map(){
            text
        }
    }

    @Composable
    fun CalendarDay(
        onClick: () -> Unit,
        date: LocalDate,
        isSelected: Boolean,
        isVisibleMonth: Boolean
    ) {

        var backColor = Color.White

        val isToday = date.isEqual(LocalDate.now())

        if(isToday) {
            backColor = Color.Green
        }

        val fontColor = remember(isVisibleMonth) {
            when {
                isToday -> Color.White
                !isVisibleMonth -> Color.DarkGray.copy(alpha = 0.3f)
                date.dayOfWeek == DayOfWeek.SUNDAY -> Color.Red
                else -> Color.Black
            }
        }

        Surface(
            onClick = onClick,
            color = Color.White,
            shape = RoundedCornerShape(Dp(6f)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = Dp(2.5f))
                        .size(Dp(20f))
                        .background(backColor)
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = fontColor
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Calendar(
        modifier: Modifier,
        state: CalendarState,
        onClick: () -> Unit
    ) {

        val pagerState = rememberPagerState(initialPage = 1,
            initialPageOffsetFraction = 0.3F,
            pageCount = { 3 })

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Header(state)
            Weekdays()
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) { page ->

                var moveMonth = state.currentYM.value

                when (page) {
                    0 -> {
                        moveMonth = state.currentYM.value.minusMonths(1)
                    }
                    2 -> {
                        moveMonth = state.currentYM.value.plusMonths(1)
                    }
                }

                val dayOfMonth = remember { state.getDayOfMonth(moveMonth) }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    dayOfMonth.chunked(7).forEach { week ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            week.forEach { date ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    val isSelected = remember(date, state.selectedDate) { date == state.selectedDate }
                                    val isVisibleMonth = remember { YearMonth.from(date) == moveMonth }

                                    CalendarDay(
                                        date = date,
                                        isSelected = isSelected,
                                        isVisibleMonth = isVisibleMonth,
                                        onClick = {
                                            state.selectedDate = date
                                            onClick()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            LaunchedEffect(pagerState) {
                snapshotFlow {
                    Triple(
                        pagerState.currentPage,
                        pagerState.targetPage,
                        pagerState.isScrollInProgress
                    )
                }
                .distinctUntilChanged()
                .collect { (page, target, isScroll) ->
                    if(!isScroll && page == target) {
                        state.currentYM.value = state.currentYM.value.plusMonths(((page - 1).toLong()))
                        pagerState.scrollToPage(1)
                    }
                }
            }
        }
    }

    @Composable
    fun Weekdays() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { dayText ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = dayText,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (dayText == "일") Color.Red else Color.Black
                    )
                }
            }
        }
    }

    @Composable
    fun Header(state: CalendarState) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            // Handle Year Click if needed
                        }
                    )
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            block = { append(state.currentYM.value.run { takeIf { year != LocalDate.now().year }?.let { "${year}. $monthValue" } ?: "$monthValue" }) },
                            style = SpanStyle(
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Medium,
                                baselineShift = BaselineShift(-0.015f)
                            )
                        )
                        state.currentYM.value.takeIf { it.year == LocalDate.now().year }?.let { append("월") }
                    },
                    fontSize = 22.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}