package com.example.exchangerates.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.constant.API_KEY
import com.example.exchangerates.dto.LiveExchangeDto
import com.example.exchangerates.model.GetLiveModel
import com.example.exchangerates.repository.OperationRepository
import com.example.exchangerates.status.LiveExchangeStatus
import com.example.exchangerates.util.HttpResponseModel
import kotlinx.coroutines.launch

class LiveViewModel(app: Application): AndroidViewModel(app) {

    private val repository: OperationRepository = OperationRepository.getInstance()

//    private var _viewState: MutableLiveData<LiveState> = MutableLiveData()
//    val viewState: LiveData<LiveState> = _viewState

    private var _loadingStatus: MutableLiveData<LiveExchangeStatus> = MutableLiveData()
    val loadingStatus: LiveData<LiveExchangeStatus> = _loadingStatus

    private var _data: MutableLiveData<GetLiveModel> = MutableLiveData()
    val data: LiveData<GetLiveModel> = _data

    init {
        _loadingStatus.value = LiveExchangeStatus.Idle
    }

    fun getLiveExchangeRates(
        source: String,
        currency: String,
        format: Int
    ){
        viewModelScope.launch {
            _loadingStatus.value = LiveExchangeStatus.Loading
            when(val  result = repository.getLiveExchangeRates(
                LiveExchangeDto(
                    key = API_KEY,
                    source = source,
                    currencies = currency,
                    format = format
                )
            )){
                is HttpResponseModel.Success -> {
                    _loadingStatus.value = LiveExchangeStatus.Success
                    _data.value = result.data!!
                    Log.d("123414", _data.value.toString())
                }
                is HttpResponseModel.Error -> {
                   _loadingStatus.value = LiveExchangeStatus.Error(
                       code = result.code,
                       message = result.message
                   )
                }
            }
        }
    }
}