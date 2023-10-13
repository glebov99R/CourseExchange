package com.example.exchangerates

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

//val tv = findViewById<TextView>(R.id.tv)
//val b = findViewById<Button>(R.id.b)
//
//b.setOnClickListener {
//    CoroutineScope(Dispatchers.IO).launch {
//        val rates = OperationRepository
//            .getInstance()
//            .getLiveExchangeRates(
//                LiveExchangeDto(
//                    key = KEY,
//                    source = "EUR",
//                    currencies = listOf("RUB"),
//                    format = 1
//                )
//            )
//        runOnUiThread {
//            when(rates){
//                is HttpResponseModel.Success -> {
//                    tv.text = "${rates.data!!.quotes.rub} ${rates.data.source}"
//                }
//
//                is HttpResponseModel.Error -> {
//
//                }
//            }
//        }
//    }
//
//}


//// Создаем объект SimpleDateFormat для форматирования даты и времени
//val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
//
//// Преобразовываем временную метку в объект Date
//val date = Date(timestamp)
//
//// Форматируем объект Date в строку с заданным форматом
//val formattedDate = sdf.format(date)