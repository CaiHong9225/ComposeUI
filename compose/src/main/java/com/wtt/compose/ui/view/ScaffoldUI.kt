package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewScaffold(){
    ScaffoldUI();
}
@Composable
fun ScaffoldUI() {
    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf("Songs", "Artists", "Playlists")

    Scaffold(
        topBar = {  // topBar 属性用于设置 AppBar
            TopAppBar(
                title = {  // 可设置标题
                    Text(text = "LayoutInCompose")
                },
                actions = {  // 设置 AppBar 上的按钮 Button
                    IconButton(onClick = { /*TODO*/ }) {
                        // Icon 系统为我们提供了许多常见的 Icon
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {  // bottomBar 可用于设置 BottomNavigation
            BottomNavigation() {
                navItems.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Face, contentDescription = null) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) {
        BodyContent(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        )
    }
}
@Composable
fun BodyContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hello Compose!")
        Text(text = "这个是页面内容！")
    }
}