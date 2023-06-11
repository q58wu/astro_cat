package com.example.astropicoftheday.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astropicoftheday.infra.RetrofitBuilder
import com.example.astropicoftheday.model.PetCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PetCardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow(listOf<PetCard>())
    val uiState: StateFlow<List<PetCard>>
        get() = _cards.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean>
        get() = _isLoadingMore.asStateFlow()


    private suspend fun getPets() {
        val cats = RetrofitBuilder.catApiService.getCatsImage(5)
        val dogs = RetrofitBuilder.dogApiService.getDogsImage(5)
        val mixer = mutableListOf<PetCard>().apply {
            addAll(cats)
            addAll(dogs)
            shuffle()
        }
        _cards.value  = mixer
    }

    private suspend fun getMorePets() {
        val cats = RetrofitBuilder.catApiService.getCatsImage()
        val dogs = RetrofitBuilder.dogApiService.getDogsImage()
        val mixer = mutableListOf<PetCard>().apply {
            addAll(cats)
            addAll(dogs)
            shuffle()
        }
        _cards.value  += mixer
    }

    fun refresh(){
        viewModelScope.launch(Dispatchers.IO){
            _isRefreshing.value = true
            delay(500L)
            getPets()
            _isRefreshing.value = false
        }
    }

    fun refreshForMore(){
        viewModelScope.launch(Dispatchers.IO){
            _isLoadingMore.value = true
            delay(2000L)
            getMorePets()
            _isLoadingMore.value = false
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.value = true
            delay(500L)
            getPets()
            _isRefreshing.value = false
        }
    }
}