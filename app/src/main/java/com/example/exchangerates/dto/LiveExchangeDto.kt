package com.example.exchangerates.dto

data class LiveExchangeDto(
    val key: String, // Ключ авторизации
    val source: String, // Наименование валюты
    val currencies: String, // Список валют
    val format: Int // Формат получения
)





