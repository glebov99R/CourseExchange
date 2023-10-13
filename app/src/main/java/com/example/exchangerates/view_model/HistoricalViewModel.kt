package com.example.exchangerates.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.constant.API_KEY
import com.example.exchangerates.dto.HistoricalDto
import com.example.exchangerates.model.GetHistoricalModel
import com.example.exchangerates.repository.OperationRepository
import com.example.exchangerates.status.HistoricalExchangeRatesStatus
import com.example.exchangerates.util.HttpResponseModel
import kotlinx.coroutines.launch

class HistoricalViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: OperationRepository = OperationRepository.getInstance()

    private var _loadingStatus: MutableLiveData<HistoricalExchangeRatesStatus> = MutableLiveData()
    val loadingStatus: LiveData<HistoricalExchangeRatesStatus> = _loadingStatus

    private var _data: MutableLiveData<GetHistoricalModel> = MutableLiveData()
    val data: LiveData<GetHistoricalModel> = _data

    init {
        _loadingStatus.value = HistoricalExchangeRatesStatus.Idle
    }

    fun getHistoricalExchangeRates(
        date: String,
        source: String,
        format: Int,
    ) {
        viewModelScope.launch {
            _loadingStatus.value = HistoricalExchangeRatesStatus.Loading
            when (val result = repository
                .getHistoricalExchangeRates(
                    HistoricalDto(
                        key = API_KEY,
                        date = date,
                        source = source,
                        format = format
                    )
                )) {
                is HttpResponseModel.Success -> {
                    _loadingStatus.value = HistoricalExchangeRatesStatus.Success
                    _data.value = result.data!!
                }

                is HttpResponseModel.Error -> {
                    _loadingStatus.value = HistoricalExchangeRatesStatus.Error(
                        result.code,
                        result.message
                    )
                }
            }
        }
    }
}