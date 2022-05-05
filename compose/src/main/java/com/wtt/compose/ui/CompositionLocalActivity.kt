package com.wtt.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val ActiveUser =
    compositionLocalOf<String> { error("No active user found!") }//这个需要被定义为可被静态引用的实例或者被@Compose修饰的公共组合中，如果没有提供值的话会报错误

//用来演示组合分层的问题
class CompositionLocalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val text = "你好啊!!!"
        setContent {
            body(text)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreViewUi() {
    val text = "你好啊!!!"
    body(text = text)
}

@Composable
private fun body(text: String) {
    //比如在这里定义也是可以的，这样就避免了全局静态变量的那种很糟糕的编码体验，同使也保留了组合隔离的特性
    val text = staticCompositionLocalOf<String> { "" }
    CompositionLocalProvider(ActiveUser provides text.toString()) {
        Column {
            content()
        }
    }
}

@Composable
private fun content() {
    child()
}

@Composable
private fun child() {//在这里并不需要知道传进来的值，所以可以很方便的保持组件的独立性，当然这个也可以隔好几层，只要在使用前定义就好
    val text = ActiveUser.current
    Text(text)
}

//验证staticCompositionLocalOf与compositionLocalOf异同
var isStatic = false
var compositionLocalName = ""
val currentLocalColor = if (isStatic) {
    compositionLocalName = "StaticCompositionLocal 场景"
    staticCompositionLocalOf { Color.Black }
} else {
    compositionLocalName = "DynamicCompositionLocal 场景"
    compositionLocalOf { Color.Black }
}

@Preview(showBackground = true)
@Composable
fun PreViewUi2() {
    CompositionLocalDemo()
}

var recomposeFlag = "Init"

@Composable
fun CompositionLocalDemo() {
    var color by remember { mutableStateOf(Color.Green) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${compositionLocalName}")
            Spacer(Modifier.height(20.dp))
            CompositionLocalProvider(
                currentLocalColor provides color
            ) {
                TaggedBox("Wrapper: ${recomposeFlag}", 400.dp, Color.Red) {
                    TaggedBox("Middle: ${recomposeFlag}", 300.dp, currentLocalColor.current) {
                        TaggedBox("Inner: ${recomposeFlag}", 200.dp, Color.Yellow)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    color = Color.Blue
                }
            ) {
                Text(text = "Change Theme")
            }
        }
    }
    recomposeFlag = "Recompose111"
}

@Composable
fun TaggedBox(tag: String, size: Dp, background: Color, content: @Composable () -> Unit = {}) {
    Column(
        modifier = Modifier
            .size(size)
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = tag)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
