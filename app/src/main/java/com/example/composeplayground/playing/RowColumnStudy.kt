package com.example.composeplayground.playing

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.composeplayground.R
import com.example.composeplayground.notification

@Composable
fun RowColumnUnderstanding(context: Context) {

    Row(
        Modifier
            .background(colorResource(id = R.color.teal_200))
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {


            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.padding(start = 26.dp)
            )

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Android is better",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                notification(context)
            }) {
                Text(text = "Button")
            }
        }
        Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.padding(start = 26.dp)
            )
    }

}

@Composable
fun ComposeText() {
    Text("Hello team :) ")
    Text("Welcome to this workshop")
}

@Composable
fun ApplyGradientTest() {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.Blue)) {
        Text(
            text = "Some test text",
            color = Color.White,
            modifier = Modifier
            .padding(20.dp)
            .gradientVertical(
                size.height.toFloat(),
                0f,
                listOf(
                    Color.Gray,
                    Color.Transparent)
            ), onTextLayout = {
            size = it.size
        })
    }
}

fun Modifier.gradientVertical(startY: Float, endY: Float, colors: List<Color>): Modifier {

    val gradient = Brush.verticalGradient(
        colors = colors,
        startY = startY,
        endY = endY
    )

    return this.background(gradient).alpha(0.1f)
}

@Preview
@Composable
fun RowColumnUnderstandingPreview() {
    RowColumnUnderstanding(LocalContext.current)
}