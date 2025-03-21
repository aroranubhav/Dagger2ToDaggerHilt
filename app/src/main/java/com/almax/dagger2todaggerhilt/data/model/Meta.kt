package com.almax.dagger2todaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("status")
    val statusCode: Int,
    @SerializedName("msg")
    val status: String = "",
    @SerializedName("response_id")
    val responseId: String = ""
)
