package com.almax.dagger2todaggerhilt.data.model

data class GifResponse(
    val meta: Meta,
    val data: List<GifData> = arrayListOf(),
    val pagination: Pagination
)
