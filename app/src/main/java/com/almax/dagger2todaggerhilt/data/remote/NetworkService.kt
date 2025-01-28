package com.almax.dagger2todaggerhilt.data.remote

import com.almax.dagger2todaggerhilt.data.model.CoinResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("coins")
    suspend fun getCoins(): List<CoinResponse>
}