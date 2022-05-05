package com.wtt.ourcompose.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewTextField(){
    TextFieldUI(LocalContext.current);
}
@Preview(showBackground = true)
@Composable
fun PreviewTextField1(){
    OutlinedTextFieldUI();
}
@Preview(showBackground = true)
@Composable
fun PreviewTextField2(){
    BasicTextFieldUI();
}
@Composable
fun TextFieldUI(context: Context) {
    // 定义一个可观测的text，用来在TextField中展示
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        value = text, // 显示文本
        onValueChange = { text = it }, // 文字改变时，就赋值给text
        label = { Text(text = "Input") }, // label是Input
        visualTransformation = PasswordVisualTransformation(), // 展示为密文  password
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), // 将键盘的回车键定义为搜索
        isError = true, // 展示错误提示
        shape = RoundedCornerShape(16.dp),// 指定圆角矩形
        // 头部图标，设置为搜索
        leadingIcon = @Composable {
            Image(
                imageVector = Icons.Filled.Search, // 搜索图标
                contentDescription = null,
                modifier = Modifier.clickable {
                    Toast.makeText(
                        context,
                        "search $text",
                        Toast.LENGTH_SHORT
                    ).show()
                }) // 给图标添加点击事件，点击就吐司提示内容
        },
        // 尾部图标，设置为清除
        trailingIcon = @Composable {
            Image(imageVector = Icons.Filled.Clear, // 清除图标
                contentDescription = null,
                modifier = Modifier.clickable { text = "" }) // 给图标添加点击事件，点击就清空text
        },
        placeholder = @Composable { Text(text = "This is placeholder") }, // 不输入内容时的占位符
    )
}

@Composable
fun OutlinedTextFieldUI() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(value = text,
        label = { Text(text = "Input something") },
        onValueChange = { text = it })
}

@Composable
fun BasicTextFieldUI() {
    var text by remember { mutableStateOf("hello") }
    BasicTextField(
        value = text,
        modifier = Modifier
            .background(Color.Red)
            .clip(RoundedCornerShape(10.dp)),
        onValueChange = { text = it },
    )
}