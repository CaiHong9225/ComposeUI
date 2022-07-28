package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewBox(){
    BoxUI();
}
@Composable
fun BoxUI() {
    Box(
        modifier = Modifier
            .size(width = 200.dp, height = 200.dp)
            .background(Color.Red),
        contentAlignment = Alignment.Center, // 内部元素居中对齐
        propagateMinConstraints = false
    ) {
        Text(
            text = "BoxLayout", modifier = Modifier
                .background(Color.Green), textAlign = TextAlign.Center
        )
    }
}