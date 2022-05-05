package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi

@Preview(showBackground = true)
@Composable
fun PreviewLazyColumn(){
    ScrollingList()
}

@ExperimentalCoilApi
@Composable
fun ScrollingList() {
    val listSize = 100
    // 使用 rememberLazyListState 保存滚动的位置
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(listSize) {
            ImageListItem(index = it)
            Divider(color = Color.Blue, thickness = 1.5.dp, startIndent = 10.dp)
        }
    }

}