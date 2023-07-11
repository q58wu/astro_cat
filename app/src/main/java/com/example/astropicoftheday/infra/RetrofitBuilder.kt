package com.example.astropicoftheday.infra

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    //private const val ASTRO_BASE_URL = "https://api.nasa.gov/planetary/apod/"
    private const val CAT_BASE_URL = "https://api.thecatapi.com/v1/"
    private const val DOG_BASE_URL = "https://api.thedogapi.com/v1/"
    private const val JOKES_BASE_URL = "https://v2.jokeapi.dev/joke/"

/*    private fun getAstroRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ASTRO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    *//*.addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter(
                                "api_key",
                                "EMRbinyGiXQ3Zu1tLrDWT6qoszIXCZYMidTR57Yv"
                            )
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }*//*
                    .build())
            .build()
    }*/

    private fun getCatRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CAT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            /*.client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().header("x-api-key",
                            "live_mXr25fx3GIYIy9iSjV9NzDGv9BoFt7ujM9GjP2SPCvA45bE7vc2mzv22LSft9VkW").build())
                    }
                    .build())*/
            .build()
    }

    private fun getDogRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DOG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            /*.client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().header("x-api-key",
                            "live_mXr25fx3GIYIy9iSjV9NzDGv9BoFt7ujM9GjP2SPCvA45bE7vc2mzv22LSft9VkW").build())
                    }
                    .build())*/
            .build()
    }

    private fun getJokesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JOKES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            /*.client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().header("x-api-key",
                            "live_mXr25fx3GIYIy9iSjV9NzDGv9BoFt7ujM9GjP2SPCvA45bE7vc2mzv22LSft9VkW").build())
                    }
                    .build())*/
            .build()
    }

    //val astroApiService: AstroApiService = getAstroRetrofit().create(AstroApiService::class.java)
    val catApiService: CatApiService = getCatRetrofit().create(CatApiService::class.java)
    val dogApiService: DogApiService = getDogRetrofit().create(DogApiService::class.java)
    val jokesApiService: JokesApiService = getJokesRetrofit().create(JokesApiService::class.java)
}