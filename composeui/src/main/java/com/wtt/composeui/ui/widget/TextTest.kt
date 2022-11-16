package com.wtt.composeui.ui.example

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


/**
 * Created by Wangzhan on 2022/3/4
 *
 * @descr
 */
class TextTest {
    @Composable
    fun TextTest(name: String, clickAction: (String) -> Unit) {
        Text(
            text = "Student:$name",
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { clickAction(name) }
        )
    }
}