package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.wtt.compose.R

@Preview(showBackground = true)
@Composable
fun PreviewPaddingUI(){
    ImageListItem(1)
}
@ExperimentalCoilApi
@Composable
fun ImageListItem(index: Int) {    // 列表 item 布局
    // Row 可设置竖直方向上的对齐方式
    Row(verticalAlignment = Alignment.CenterVertically) {
        //使用coil加载Img
        val imgUrl = remember {
            mutableStateOf(
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fqqpublic.qpic.cn%2Fqq_public%2F0%2F0-2564193147-DA6C44FF8D94851F4791DAD7F95B8EBA%2F0%3Ffmt%3Djpg%26size%3D58%26h%3D960%26w%3D720%26ppv%3D1.jpg&refer=http%3A%2F%2Fqqpublic.qpic.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1636628506&t=ea0f46943b11b861b98ac1e550b5843e"
            )
        }
        Image(
            painter = rememberImagePainter(                             //第三方图片控件coil里的一个方法
                data = imgUrl,                                          //data是数据源，可以是url也可以是drawable资源
                builder = {
                    transformations(
                        RoundedCornersTransformation(3f)          //圆角的转换
                    )
                    placeholder(R.mipmap.android)                    //占位符
                    error(R.mipmap.android)                  //加载失败的图片
                },
                onExecute = ImagePainter.ExecuteCallback.Default         //在 ImagePainter 启动图像请求之前立即调用。 返回“true”以继续请求。 返回 'false' 以跳过执行请求。
            ),
            contentDescription = "网络加载图片",
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
            alpha = 1f
        )
        Spacer(modifier = Modifier.width(10.dp)) // Spacer 也可设置边距
        Text(text = "Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}