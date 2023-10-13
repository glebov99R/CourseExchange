package com.example.exchangerates.dto

data class HistoricalDto(
    val key: String, // API ключ
    val date: String, // Дата
    val source: String, // Вадюта
    val format: Int // Формат
)