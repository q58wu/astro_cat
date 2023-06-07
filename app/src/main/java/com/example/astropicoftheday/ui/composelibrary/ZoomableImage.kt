package com.example.astropicoftheday.ui.composelibrary

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.astropicoftheday.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ZoomableImage(
    model: String,
    contentDescription: String? = null,
    onBackHandler: () -> Unit,
) {
    val angle by remember { mutableStateOf(0f) }
    var zoom by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.value
    val screenHeight = configuration.screenHeightDp.dp.value

    BackHandler { onBackHandler() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .combinedClickable(
                //interactionSource = remember { MutableInteractionSource() },
                //indication = null,
                onClick = {},
                onDoubleClick = {
                    zoom = if (zoom > 1f) 1f
                    else 3f
                }
            )
    ) {
        AsyncImage(
            model = model,
            placeholder = debugPlaceholder(debugPreview = R.drawable.ic_launcher_foreground),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .graphicsLayer(
                    scaleX = zoom,
                    scaleY = zoom,
                    rotationZ = angle
                )
                /*.pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { _, pan, gestureZoom, _ ->
                            zoom = (zoom * gestureZoom).coerceIn(1F..4F)
                            if (zoom > 1) {
                                val x = (pan.x * zoom)
                                val y = (pan.y * zoom)
                                val angleRad = angle * PI / 180.0

                                offsetX =
                                    (offsetX + (x * cos(angleRad) - y * sin(angleRad)).toFloat()).coerceIn(
                                        -(screenWidth * zoom)..(screenWidth * zoom)
                                    )
                                offsetY =
                                    (offsetY + (x * sin(angleRad) + y * cos(angleRad)).toFloat()).coerceIn(
                                        -(screenHeight * zoom)..(screenHeight * zoom)
                                    )
                            } else {
                                offsetX = 0F
                                offsetY = 0F
                            }
                        }
                    )
                }*/
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xfff)
@Composable
private fun preview1(){
    ZoomableImage("", "", {})
}