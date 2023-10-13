package com.example.exchangerates.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("USDRUB")
    val rub: Double
)

