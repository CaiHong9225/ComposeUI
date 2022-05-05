package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wtt.ourcompose.R
import com.wtt.ourcompose.ui.TextUI

@Preview(showBackground = true)
@Composable
fun PreviewImage(){
    ImageUI();
}
@Composable
fun ImageUI() {
    Image(
        painter = painterResource(id = R.mipmap.android),
        contentDescription = stringResource(id = R.string.str_image_description),
        modifier = Modifier
            .size(width = 100.dp, height = 100.dp) //指定size为200dp
            .background(Color.White, RoundedCornerShape(10.dp))
            .clip(CircleShape), // 圆形图
        contentScale = ContentScale.Inside, // Inside显示
        alignment = Alignment.Center//图片居中
    )
}