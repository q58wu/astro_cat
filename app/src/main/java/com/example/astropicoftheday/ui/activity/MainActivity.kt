package com.example.astropicoftheday.ui.activity

import android.os.Bundle
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.astropicoftheday.R
import com.example.astropicoftheday.ui.composelibrary.PetImageCard
import com.example.astropicoftheday.ui.composelibrary.extension.OnBottomReached
import com.example.astropicoftheday.ui.theme.AstroAndCatTheme
import com.example.astropicoftheday.ui.viewmodel.PetCardsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : ComponentActivity() {

    //private val astroViewModel: AstroCardsViewModel by viewModels()
    private val petsViewModel: PetCardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetHomeScreen(petsViewModel = petsViewModel)
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun PetHomeScreen(petsViewModel: PetCardsViewModel) {
    AstroAndCatTheme {
        val petsCards = petsViewModel.uiState.collectAsState().value
        val isRefreshing by petsViewModel.isRefreshing.collectAsState()
        val isLoadingMore by petsViewModel.isLoadingMore.collectAsState()
        val pullRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
        val lazyStaggerGridListState = rememberLazyStaggeredGridState()
        SwipeRefresh(
            state = pullRefreshState,
            onRefresh = petsViewModel::refresh,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                )
            },
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.wrapContentHeight(),
                            title = {
                                Text(text = "CutiePet", textAlign = TextAlign.Center)
                            },
                            navigationIcon = {
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.cat_and_dog_logo),
                                    contentDescription = null
                                )
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.tertiary
                            )
                        )
                    }
                ) {
                    Column(modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        LazyVerticalStaggeredGrid(
                            modifier = Modifier
                                .wrapContentHeight()
                                .weight(1F)
                                .padding(horizontal = 4.dp),
                            columns = StaggeredGridCells.Fixed(2),
                            contentPadding = it,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalItemSpacing = 4.dp,
                            state = lazyStaggerGridListState
                        )
                        {
                            petsCards.forEach {
                                item {
                                    PetImageCard(url = it.url)
                                }
                            }
                            if(!isRefreshing){
                                item{
                                    CircularProgressIndicator(modifier = Modifier.requiredSize(24.dp),
                                        color = MaterialTheme.colorScheme.primary)
                                }
                            }


                        }
                        /*if(isLoadingMore){
                            CircularProgressIndicator(modifier = Modifier.height(12.dp))
                        }*/
                        /*else{
                            Text(text = "Loading More...", modifier = Modifier.height(24.dp))
                        }*/
                        lazyStaggerGridListState.OnBottomReached {
                            petsViewModel.refreshForMore()
                        }
                    }
                }
            }
        }
    }
}
