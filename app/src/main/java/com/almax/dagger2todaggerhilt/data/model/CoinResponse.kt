package com.almax.dagger2todaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val rank: Int = 0,
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("is_active")
    val isActive: Boolean = false,
)
