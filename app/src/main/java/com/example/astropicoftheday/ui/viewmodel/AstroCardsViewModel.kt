package com.example.astropicoftheday.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astropicoftheday.infra.RetrofitBuilder
import com.example.astropicoftheday.model.BaseCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AstroCardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow(listOf<BaseCard>())
    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<List<BaseCard>>
        get() = _cards


    suspend fun getCards(startDate: String, endDate: String) {
        _cards.value  = RetrofitBuilder.astroApiService.getCard(startDate, endDate)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            //getCards("2023-05-28", "2023-06-02")
        }
    }

}