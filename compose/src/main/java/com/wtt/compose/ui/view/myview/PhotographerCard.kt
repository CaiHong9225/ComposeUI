package com.wtt.compose.ui.view.myview

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wtt.compose.R


/**
 * Created by Wangzhan on 2022/7/27
 *
 * @descr
 */
@Preview
@Composable
fun PhotographerCardPreview() {
    PhotographerCardView()
}

@Composable
fun PhotographerCardView() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(10.dp))
            .size(320.dp, 100.dp)
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(R.mipmap.tesila),
            contentDescription = stringResource(id = R.string.str_image_description),
            modifier = Modifier
                .size(width = 50.dp, height = 50.dp) //指定size为200dp
                .background(Color.White, RoundedCornerShape(50.dp))
                .clip(CircleShape), // 圆形图
            contentScale = ContentScale.Crop, // Inside显示
            alignment = Alignment.Center//图片居中
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = "Tesla",
                fontStyle = FontStyle.Normal,
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "An electrical expert",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}