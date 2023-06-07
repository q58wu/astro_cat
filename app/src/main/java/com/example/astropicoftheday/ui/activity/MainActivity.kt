package com.example.astropicoftheday.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.astropicoftheday.ui.composelibrary.ImageCard
import com.example.astropicoftheday.ui.composelibrary.PetImageCard
import com.example.astropicoftheday.ui.composelibrary.ZoomableImage
import com.example.astropicoftheday.ui.theme.AstroAndCatTheme

class MainActivity : ComponentActivity() {

    private val astroViewModel: AstroCardsViewModel by viewModels()
    private val petsViewModel: PetCardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetHomeScreen(petsViewModel = petsViewModel)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PetHomeScreen(petsViewModel: PetCardsViewModel){
    val petsCards = petsViewModel.uiState.collectAsState().value

    AstroAndCatTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Astro Pic of the Day")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            ) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = it,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalItemSpacing = 4.dp
                )
                {
                    petsCards.forEach {
                        item{
                            PetImageCard(url = it.url, )
                        }
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstroHomeScreen(
    astroCardsViewModel: AstroCardsViewModel
){
    val cards = astroCardsViewModel.uiState.collectAsState().value

    AstroAndCatTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Astro Pic of the Day")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            ) { values ->
                LazyColumn(contentPadding = values) {
                    item {
                        cards.forEach {
                            ImageCard(
                                title = it.title,
                                description = it.body?: "",
                                imageUrl = it.url,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

