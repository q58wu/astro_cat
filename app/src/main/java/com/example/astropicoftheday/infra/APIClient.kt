package com.example.astropicoftheday.infra

import com.example.astropicoftheday.model.BaseCard
import com.example.astropicoftheday.model.JokesWrapper
import com.example.astropicoftheday.model.Pet
import retrofit2.http.GET
import retrofit2.http.Query

/*interface AstroApiService {
    @GET
    suspend fun getCard( @Query("start_date") startDate: String,
                         @Query("end_date") endDate: String): List<BaseCard>
}*/

interface CatApiService {
    @GET("images/search")
    suspend fun getCatsImage(@Query("limit") limit: Int = 10): List<Pet>
}

interface DogApiService {
    @GET("images/search")
    suspend fun getDogsImage(@Query("limit") limit: Int = 10): List<Pet>
}

interface JokesApiService {

    @GET("Any")
    suspend fun getJokes(@Query("amount") amount: Int = 10,
                         @Query("type") type: String = "twopart"): JokesWrapper
}