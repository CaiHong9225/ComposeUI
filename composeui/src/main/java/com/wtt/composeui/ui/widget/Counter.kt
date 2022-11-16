package com.wtt.composeui.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


/**
 * Created by Wangzhan on 2022/3/4
 *
 * @descr
 */

@Preview
@Composable
fun CounterPreview() {

    var number by remember { mutableStateOf(0) }

    val clickAction: () -> Unit = { number++ }

    Counter(number = number, clickAction = clickAction)
}

@Composable
fun Counter(number: Int, clickAction: () -> Unit) {

    Column() {
        Text(text = "Number:$number")
        Button(onClick = { clickAction }) {
            Text(text = "AddNumber")
        }
    }
}