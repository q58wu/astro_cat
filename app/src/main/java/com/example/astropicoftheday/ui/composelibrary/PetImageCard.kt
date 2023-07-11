package com.example.astropicoftheday.ui.composelibrary

import FavoriteButton
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.astropicoftheday.R
import kotlin.math.roundToInt


@Composable
fun PetImageCard(url: String, contentDescription: String? = null,
                 joke1: String, joke2: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .shadow(elevation = 12.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(4.dp)
    ) {

        Column() {
            Box {
                AsyncImage(
                    model = url,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                fontSize = 14.sp,
                //maxLines = 2,
                text = joke1,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(Modifier.height(4.dp).fillMaxWidth())
            Row{
                Text(
                    fontSize = 14.sp,
                    //maxLines = 1,
                    text = joke2,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(Modifier.weight(1f))
                FavoriteButton()
            }
        }


    }
}

@Preview(showBackground = true, backgroundColor = 0xfff)
@Composable
private fun preview1(){
    PetImageCard("",
        "",
        "what is love",
        "baby dont hurt me")
}