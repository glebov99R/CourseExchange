package com.example.exchangerates.status

sealed class HistoricalExchangeRatesStatus {

    object Idle: HistoricalExchangeRatesStatus()

    object Loading: HistoricalExchangeRatesStatus()

    object Success: HistoricalExchangeRatesStatus()

    data class Error(
        val code: Int,
        val message: String
    ): HistoricalExchangeRatesStatus()
}