package com.almax.dagger2todaggerhilt.data.repository

import com.almax.dagger2todaggerhilt.data.model.CoinResponse
import com.almax.dagger2todaggerhilt.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun getCoins(): Flow<List<CoinResponse>> {
        return flow {
            emit(networkService.getCoins())
        }
    }
}