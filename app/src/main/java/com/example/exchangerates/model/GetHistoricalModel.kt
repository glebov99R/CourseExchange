package com.example.exchangerates.model

import com.google.gson.annotations.SerializedName

data class GetHistoricalModel(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("terms")
    val terms: String,

    @SerializedName("privacy")
    val privacy: String,

    @SerializedName("historical")
    val historical: Boolean,

    @SerializedName("date")
    val date: String,

    @SerializedName("timestamp")
    val timestamp: Int,

    @SerializedName("source")
    val source: String,

    @SerializedName("quotes")
    val quotes: QuoteModel,

    )
