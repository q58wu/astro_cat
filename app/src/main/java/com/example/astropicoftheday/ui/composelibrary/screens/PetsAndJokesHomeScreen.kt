package com.example.astropicoftheday.ui.composelibrary.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.astropicoftheday.ui.composelibrary.PetImageCard
import com.example.astropicoftheday.ui.composelibrary.extension.OnBottomReached
import com.example.astropicoftheday.ui.viewmodel.PetCardsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PetHomeScreen(petsViewModel: PetCardsViewModel, paddingValues: PaddingValues) {

    val petsAndJokesCards = petsViewModel.uiState.collectAsState().value
    val isRefreshing by petsViewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val lazyStaggerGridListState = rememberLazyStaggeredGridState()
    SwipeRefresh(
        state = pullRefreshState,
        onRefresh = petsViewModel::refresh,
        modifier = Modifier.padding(paddingValues),
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1F)
                        .padding(horizontal = 4.dp),
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalItemSpacing = 4.dp,
                    state = lazyStaggerGridListState
                )
                {
                    petsAndJokesCards.forEach {
                        item {
                            PetImageCard(
                                url = it.url,
                                joke1 = it.jokePart1,
                                joke2 = it.jokePart2
                            )
                        }
                    }
                    // no need to load more if a manual refresh has already been triggered.
                    if (!isRefreshing) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier.requiredSize(24.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                lazyStaggerGridListState.OnBottomReached {
                    petsViewModel.refreshForMore()
                }
            }
        }
    }
}