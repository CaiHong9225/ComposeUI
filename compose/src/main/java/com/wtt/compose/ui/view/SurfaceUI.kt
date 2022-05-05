package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun PreviewSurface(){
    SurfaceUI();
}

@Composable
fun SurfaceUI() {
    Surface(
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(0.5.dp, Color.Green),  // 边框
        elevation = 10.dp,  // 高度
        modifier = Modifier
            .padding(10.dp),  // 外边距
        contentColor = Color.Blue,
    ) {
        Surface(
            modifier = Modifier
                .clickable { }  // 点击事件在 padding 前，则此padding为内边距
                .padding(10.dp),
            contentColor = Color.Magenta  // 会覆盖之前 Surface 设置的 contentColor
        ) {
            Text(text = "This is a SurfaceDemo~")
        }
    }
}