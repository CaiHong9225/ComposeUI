package com.wtt.ourcompose.ui.view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun PreviewConstraintLayout(){
    ConstraintLayoutUI();
}
@Composable
fun ConstraintLayoutUI() {
    ConstraintLayout {
        // 初始化声明两个元素，如果只声明一个，则可用 createRef() 方法
        // 这里声明的类似于 View 的 id
        val (button, text) = createRefs()

        Button(
            onClick = {},
            // constrainAs() 将 Composable 组件与初始化的引用关联起来
            // 关联之后就可以在其他组件中使用并添加约束条件了
            modifier = Modifier.constrainAs(button) {
                // 熟悉 ConstraintLayout 约束写法的一眼就懂
                // parent 引用可以直接用，跟 View 体系一样
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
        ) {
            Text("Button")
        }

        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            start.linkTo(button.start)
            centerHorizontallyTo(parent)  // 摆放在 ConstraintLayout 水平中间
        })
    }
}
