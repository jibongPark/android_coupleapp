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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalendarView() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top,
    modifier =
      Modifier.fillMaxSize().padding(all = 4.dp).padding(top = 14.dp).background(Color(0xFFFFA500)),
  ) {
    Box(
      modifier = Modifier.fillMaxWidth().weight(1.0f).padding(4.dp).background(Color.Yellow),
    ) {
    }
  }
}

//@Composable
//fun Header(state: CalendarState) {
//
//  Box(
//    modifier = Modifier
//      .fillMaxWidth()
//      .height(56.dp),
//    contentAlignment = Alignment.Center
//  ) {
//    Row(
//      verticalAlignment = Alignment.CenterVertically,
//      modifier = Modifier
//        .padding(start = 8.dp)
//        .clickable(
//          interactionSource = remember { MutableInteractionSource() },
//          indication = null,
//          onClick = {
//            // Handle Year Click if needed
//          }
//        )
//    ) {
//      Text(
//        text = buildAnnotatedString {
//          withStyle(
//            block = { append(state.currentYM.value.run { takeIf { year != LocalDate.now().year }?.let { "${year}. $monthValue" } ?: "$monthValue" }) },
//            style = SpanStyle(
//              fontSize = 23.sp,
//              fontWeight = FontWeight.Medium,
//              baselineShift = BaselineShift(-0.015f)
//            )
//          )
//          state.currentYM.value.takeIf { it.year == LocalDate.now().year }?.let { append("월") }
//        },
//        fontSize = 22.sp,
//        lineHeight = 22.sp,
//        fontWeight = FontWeight.SemiBold,
//      )
//    }
//  }
//}

@Preview
@Composable
fun CalendarViewPreview() {
  CalendarView()
}
