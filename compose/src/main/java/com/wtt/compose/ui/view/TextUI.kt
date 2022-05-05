package com.wtt.ourcompose.ui

import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun PreviewText(){
    TextUI();
}
@Composable
fun TextUI() {
    val text = "this is compose text demo, which likes TextView in android native xml layout"
    Text(
        text = text, // 文字
        color = Color.Green, // 字体颜色
        fontSize = 16.sp, // 字体大小
        fontStyle = FontStyle.Italic, // 斜体
        fontWeight = FontWeight.Bold, // 粗体
        textAlign = TextAlign.Center, // 对齐方式: 居中对齐
        modifier = Modifier.width(300.dp), // 指定宽度为300dp
        maxLines = 2, // 最大行数
        overflow = TextOverflow.Ellipsis, // 文字溢出后就裁剪
        softWrap = true, // 文字过长时是否换行
        textDecoration = TextDecoration.Underline, // 文字装饰，这里添加下划线
    )
}