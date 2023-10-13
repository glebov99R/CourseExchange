package com.example.exchangerates.repository

import com.example.exchangerates.api.ApiService
import com.example.exchangerates.dto.HistoricalDto
import com.example.exchangerates.dto.LiveExchangeDto
import com.example.exchangerates.model.GetHistoricalModel
import com.example.exchangerates.model.GetLiveModel
import com.example.exchangerates.util.HttpResponseModel
import retrofit2.Response

class OperationRepository {

    companion object {
        @Volatile
        private var instance: OperationRepository? = null
        fun getInstance() = instance ?: synchronized(this) { instance ?: OperationRepository().also { instance = it } }
    }

    suspend fun getLiveExchangeRates(dto: LiveExchangeDto): HttpResponseModel<GetLiveModel> =
        send { ApiService.getInstance().getLiveExchangeRates(dto.key,dto.source,dto.currencies,dto.format) }

    suspend fun getHistoricalExchangeRates(dto: HistoricalDto): HttpResponseModel<GetHistoricalModel> =
        send { ApiService.getInstance().getHistoricalExchangeRates(dto.key,dto.date,dto.source,dto.format) }

    private suspend fun <R> send(f: suspend () -> Response<R>): HttpResponseModel<R> {
        return f().run {
            return@run when (isSuccessful) {
                true -> HttpResponseModel.Success(body())
                false -> HttpResponseModel.Error(code(), message())
            }
        }
    }

}