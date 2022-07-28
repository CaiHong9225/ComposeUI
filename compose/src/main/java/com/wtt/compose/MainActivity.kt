package com.wtt.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.wtt.compose.entity.DemoReqData
import com.wtt.compose.ui.theme.ComposeTestTheme
import com.wtt.compose.viewmodel.MainActivityViewModel
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Preview
@Composable
fun Greeting() {
    val mainViewModel: MainActivityViewModel = viewModel()
    val data = mainViewModel.getData().collectAsLazyPagingItems()

    Column() {
        LazyColumn() {
            items(items = data) { item ->
                MessageItem(data = item)
            }
        }
        val TAG = "加载状态"
        if (data.loadState.refresh is LoadState.Loading) {
            Log.d(TAG, "正在加载~~~")
        } else if (data.loadState.refresh is LoadState.Error) {
            when ((data.loadState.refresh as LoadState.Error).error) {
                is IOException -> {
                    Log.d(TAG, "网络未连接，可在这里放置失败视图~~~")
                }
                else -> {
                    Log.d(TAG, "其他网络异常~~~")
                }
            }
        }
    }
}

@Composable
fun MessageItem(data: DemoReqData.DataBean.DatasBean?) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
            .fillMaxSize(), elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "作者：${data?.author}")
            Text(text = "作者：${data?.title}")
        }

    }
}

