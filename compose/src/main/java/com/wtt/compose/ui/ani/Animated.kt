package com.wtt.compose.ui.ani

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wtt.compose.R
import androidx.compose.runtime.R as R1


/**
 * Created by Wangzhan on 2022/5/5
 *
 * @descr  Compose的动画效果
 */
object Animated {


    @Composable
    fun testAni() {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            AnimatedVisibilityView()
            AnimatedContentView()
            AnimateContentSizeView()
            CrossfadeView()
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun AnimatedVisibilityView() {
        var visible by remember {
            mutableStateOf(false)
        }
        Column() {
            AnimatedVisibility(
                visible = visible,
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.db_launcher),
                    contentDescription = null
                )
            }

            Button(modifier = Modifier.padding(vertical = 5.dp), onClick = { visible = !visible }) {
                var value = if (visible) {
                    "隐藏"
                } else {
                    "显示"
                }
                Text(text = value)
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun AnimatedContentView() {
        Column() {
            var data by remember {
                mutableStateOf(0)
            }
            Button(onClick = { data++ }) {
                Text(text = "添加数据")
            }
            AnimatedContent(targetState = data) {
                Text(text = "数值:${data}")
            }
        }
    }

    //    Crossfade与animateContentSize
    //animateContentSize可以在尺寸大小改变的时候添加动画，Crossfade是淡入淡出动画，可用于视图切换等操作。首先来看animateContentSize的使用。
    @Composable
    private fun AnimateContentSizeView() {
        Column() {
            var message by remember {
                mutableStateOf("")
            }
            TextField(value = message, onValueChange = { message = it })

            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .animateContentSize()
            ) {

                Text(text = message)
            }
        }
    }

    @Composable
    private fun CrossfadeView() {
        var flag by remember {
            mutableStateOf(false)
        }
        Column() {
            Crossfade(targetState = flag, animationSpec = tween(3000)) {
                when (it) {
                    false -> Screen1()
                    true -> Screen2()
                }
            }
            Button(onClick = { flag = !flag }) {
                Text(text = "视图切换")
            }
        }
    }
}

@Composable
fun Screen1() {
    Box(
        Modifier
            .size(400.dp)
            .background(Color.Blue)
    ) {

        Text(text = "Scree1")
    }
}

@Composable
fun Screen2() {
    Box(
        Modifier
            .size(400.dp)
            .background(Color.Red)
    ) {
        Text(text = "Scree2")

    }
}