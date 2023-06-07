package com.example.astropicoftheday.infra

import com.example.astropicoftheday.model.BaseCard
import com.example.astropicoftheday.model.PetCard
import retrofit2.http.GET
import retrofit2.http.Query

interface AstroApiService {
    @GET
    suspend fun getCard( @Query("start_date") startDate: String,
                         @Query("end_date") endDate: String): List<BaseCard>
}

interface CatApiService {
    @GET("images/search")
    suspend fun getCatsImage(@Query("limit") limit: Int = 20): List<PetCard>
}