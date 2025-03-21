package com.almax.dagger2todaggerhilt.data.repository

import com.almax.dagger2todaggerhilt.data.model.GifData
import com.almax.dagger2todaggerhilt.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrendingGifRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun getTrendingGifs(): Flow<List<GifData>> {
        return flow {
            emit(networkService.getTrendingGifs())
        }.map {
            it.data
        }
    }
}