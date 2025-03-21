package com.almax.dagger2todaggerhilt.data.remote

import com.almax.dagger2todaggerhilt.data.model.GifResponse
import com.almax.dagger2todaggerhilt.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("offset") offset: Int = 0
    ): GifResponse
}