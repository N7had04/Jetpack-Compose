package com.example.androidbootcamp7jetpackcompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Timer(
    totalTime: Int,
) {
    var sweepAngle by remember { mutableFloatStateOf(-360f) }
    var initialValue by remember { mutableIntStateOf(totalTime) }
    var started by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = started) {
        if (started) {
            if (initialValue == 0) {
                initialValue = totalTime
                sweepAngle = -360f
            }
            while (initialValue > 0) {
                delay(1000L)
                initialValue -= 1
                sweepAngle = -360 * (initialValue / totalTime.toFloat())
                if (initialValue == 0) {
                    started = false
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) {
        Canvas(
            modifier = Modifier.align(Alignment.Center).size(200.dp)
        ) {
            drawArc(
                color = Color.LightGray,
                startAngle = 270f,
                sweepAngle = -360f,
                useCenter = false,
                style = Stroke(
                    width = 12.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = Color.Green,
                startAngle = 270f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = 12.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = initialValue.toString(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { started = !started },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 300.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if (started) Color.Red else Color.Green)
        ) {
            Text(
                text = if (started) "Stop" else "Start"
            )
        }
        Button(
            onClick = {
                started = false
                initialValue = totalTime
                sweepAngle = -360f },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(
                text = "Reset"
            )
        }
    }
}