package com.example.exchangerates.status

sealed class LiveExchangeStatus {

    object Idle: LiveExchangeStatus()

    object Loading: LiveExchangeStatus()

    object Success: LiveExchangeStatus()

    data class Error(
        val code: Int,
        val message: String
    ): LiveExchangeStatus()
}