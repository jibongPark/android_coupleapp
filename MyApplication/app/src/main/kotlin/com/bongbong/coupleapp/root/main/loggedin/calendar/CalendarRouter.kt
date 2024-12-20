/*
 * Copyright (C) 2021. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bongbong.coupleapp.root.main.loggedin.calendar

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import com.uber.rib.core.BasicViewRouter

class CalendarRouter(
  view: ComposeView,
  calendarInteractor: CalendarInteractor,
  private val parentView: ViewGroup,
  private val calendarScope: CalendarScope,
  private val childContent: ChildContent,
) : BasicViewRouter<ComposeView, CalendarInteractor>(view, calendarInteractor) {

  override fun willAttach() {
    super.willAttach()
    parentView.addView(view)
  }

  override fun willDetach() {
    parentView.removeView(view)
    super.willDetach()
  }

  class ChildContent {
    internal var fullScreenSlot: MutableState<(@Composable () -> Unit)> = mutableStateOf({})
  }
}
