package com.example.astropicoftheday.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astropicoftheday.infra.RetrofitBuilder
import com.example.astropicoftheday.model.PetAndJokeCombinedCard
import com.example.astropicoftheday.model.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PetCardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow(listOf<PetAndJokeCombinedCard>())
    val uiState: StateFlow<List<PetAndJokeCombinedCard>>
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
        val jokes = RetrofitBuilder.jokesApiService.getJokes(10).jokes
        val mixer = mutableListOf<Pet>().apply {
            addAll(cats.subList(0,5))
            addAll(dogs.subList(0,5))
            shuffle()
        }
        val petsAndJokes = mixer.mapIndexed { index, petCard ->
            PetAndJokeCombinedCard(petCard.url, jokes[index].setup, jokes[index].delivery)
        }
        _cards.value  = petsAndJokes
    }

    private suspend fun getMorePets() {
        val cats = RetrofitBuilder.catApiService.getCatsImage(5)
        val dogs = RetrofitBuilder.dogApiService.getDogsImage(5)
        val jokes = RetrofitBuilder.jokesApiService.getJokes(10).jokes
        val mixer = mutableListOf<Pet>().apply {
            addAll(cats.subList(0,5))
            addAll(dogs.subList(0,5))
            shuffle()
        }
        val petsAndJokes = mixer.mapIndexed { index, petCard ->
            PetAndJokeCombinedCard(petCard.url, jokes[index].setup, jokes[index].delivery)
        }
        _cards.value  += petsAndJokes
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