package com.example.astropicoftheday.ui.composelibrary

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.astropicoftheday.R
import kotlin.math.roundToInt


@Composable
fun PetImageCard(url: String, contentDescription: String? = null){
    Card(
        modifier = Modifier.padding(4.dp).fillMaxWidth().fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        AsyncImage(
            model = url,
            placeholder = debugPlaceholder(debugPreview = R.drawable.ic_launcher_foreground),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xfff)
@Composable
private fun preview1(){
    PetImageCard("","")
}