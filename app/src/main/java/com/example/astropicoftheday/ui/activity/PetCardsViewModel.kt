package com.example.astropicoftheday.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astropicoftheday.infra.RetrofitBuilder
import com.example.astropicoftheday.model.PetCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetCardsViewModel : ViewModel() {
    private val _cards = MutableStateFlow(listOf<PetCard>())
    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<List<PetCard>>
        get() = _cards


    suspend fun getCards() {
        _cards.value  = RetrofitBuilder.catApiService.getCatsImage()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCards()
        }
    }
}