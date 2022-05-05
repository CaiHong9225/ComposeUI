package com.wtt.ourcompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wtt.compose.R

@Preview(showBackground = true)
@Composable
fun PreviewRow(){
    RowAndColumnUI();
}

@Composable
fun RowAndColumnUI() {
    Row(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .background(color = Color.White)
                .padding(all = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.android),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = "当日新鲜苹果",
                fontSize = 20.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Row(
                modifier = Modifier.padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "￥500",
                    fontSize = 14.sp,
                    color = Color(0xff9f8722)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "23人免费拿", fontSize = 12.sp)
            }
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "免费送")
                Spacer(modifier = Modifier.weight(1f))//通过设置weight让Spacer把Row撑开，实现后面的图片居右对齐的效果
                Image(
                    painter = painterResource(id = android.R.drawable.btn_star_big_on),
                    contentDescription = null,
                )
            }
        }
    }

}
