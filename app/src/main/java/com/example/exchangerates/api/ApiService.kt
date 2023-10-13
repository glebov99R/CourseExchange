package com.example.exchangerates.api

import com.example.exchangerates.model.GetHistoricalModel
import com.example.exchangerates.model.GetLiveModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        @Volatile
        private var instance: ApiService? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?:  Retrofit.Builder()
                .baseUrl("http://api.currencylayer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
                .also { instance = it }
        }
    }

    @GET("live")
    suspend fun getLiveExchangeRates(
        @Query("access_key") token: String,
        @Query("source") source: String,
        @Query("currencies") currencies: String,
        @Query("format") format: Int
    ): Response<GetLiveModel>

    @GET("historical")
    suspend fun getHistoricalExchangeRates(
        @Query("access_key") token: String,
        @Query("date") date: String,
        @Query("source") source: String,
        @Query("format") format: Int
    ): Response<GetHistoricalModel>

}