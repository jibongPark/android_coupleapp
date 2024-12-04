package com.bongbong.coupleapp.rootRib

import com.bongbong.coupleapp.calendar.CalendarBuilder
import com.bongbong.coupleapp.calendar.schedule.ScheduleBuilder
import com.bongbong.coupleapp.menu.MenuBuilder
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import org.junit.Before


class RootRouterTest {

    @Mock
    lateinit var component: RootBuilder.Component

    @Mock
    lateinit var interactor: RootInteractor

    @Mock
    lateinit var view: RootView

    lateinit var router: RootRouter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        router = RootRouter(view, interactor, component, CalendarBuilder(component), ScheduleBuilder(component) ,MenuBuilder(component))
    }
}