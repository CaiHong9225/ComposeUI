package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun PreviewMyView(){
//    TextPaddingUI()
    MyOwnColumnUI()
}
/**
 * 扩展出baseLine的间距
 */
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        // 检查这个 Composable 组件是否存在 FirstBaseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        // 存在的情况下，获取 FirstBaseline 离 Composable 组件顶部的距离
        val firstBaseline = placeable[FirstBaseline]
        // 计算 Y 轴方向上 Composable 组件的放置位置
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        // 计算得出此 Composable 组件真正的 height 值
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)
@Composable
fun TextPaddingUI() {
    Row {
        Text(
            text = "大明湖畔",
            //横向间距20dp,竖向间距
            modifier = Modifier.padding(20.dp, 10.dp),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "大明湖畔",
            modifier = Modifier.firstBaselineToTop(40.dp),
            fontSize = 15.sp,
            color = Color.Red
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "大明湖畔",
            modifier = Modifier.firstBaselineToTop(60.dp),
            fontSize = 30.sp,
            color = Color.Green
        )
    }
}


/**
 * 通常情况下的自定义视图结构
 */


@Composable
fun MyOwnColumnUI() {
    MyOwnColumn(
        Modifier
            .padding(20.dp)
            .background(Color.Gray)
    ) {
        Text(
            "占满父视图1", modifier = Modifier
                .height(20.dp)
                .background(Color.Red)
        )
        Text("占满父视图2")
        Text("占满父视图3")
    }
}
/**
 * 自定义的竖向布局
 */
@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    // 此处可添加自定义的参数
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // 对 children 进行测量和放置
        val placeables = measurables.map { measurable ->
            // 测量每个 child 的尺寸
            measurable.measure(constraints)
        }
        var yPosition = 0  // 记录下一个元素竖直方向上的位置
        // Column 的宽高设置的是尽最大可能撑满父布局
        layout(constraints.maxWidth, constraints.maxHeight) {
            // 摆放 children
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, yPosition)
                yPosition += placeable.height
            }
        }
    }
}