package com.example.phoneshop.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.use_case.GetHomeProductsUseCaseImpl
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getHomeProductsUseCaseImpl: GetHomeProductsUseCaseImpl
): ViewModel() {

    private var mHomeProducts = MutableLiveData<NetworkResult<HomeModel>>()
    val liveHomeProducts: LiveData<NetworkResult<HomeModel>> get() = mHomeProducts

    fun getHomeProducts(homeApi: String) {
        viewModelScope.launch {
            mHomeProducts.value = getHomeProductsUseCaseImpl.getHomeProducts(homeApi = homeApi)
        }
    }

}