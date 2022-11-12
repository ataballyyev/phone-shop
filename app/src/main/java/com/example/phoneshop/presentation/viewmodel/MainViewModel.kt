package com.example.phoneshop.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.use_case.GetBasketProductsUseCaseImpl
import com.example.data.use_case.GetHomeProductsUseCaseImpl
import com.example.data.use_case.GetProductDetailsUseCaseImpl
import com.example.domain.model.basket.BasketModel
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getHomeProductsUseCaseImpl: GetHomeProductsUseCaseImpl,
    private val getProductDetailsUseCaseImpl: GetProductDetailsUseCaseImpl,
    private val getBasketProductsUseCaseImpl: GetBasketProductsUseCaseImpl
): ViewModel() {

    private var mHomeProducts = MutableLiveData<NetworkResult<HomeModel>>()
    val liveHomeProducts: LiveData<NetworkResult<HomeModel>> get() = mHomeProducts

    private var mProductDetails = MutableLiveData<NetworkResult<ProductDetailModel>>()
    val liveProductDetails: LiveData<NetworkResult<ProductDetailModel>> get() = mProductDetails

    private var mBasketProducts = MutableLiveData<NetworkResult<BasketModel>>()
    val liveBasketProducts: LiveData<NetworkResult<BasketModel>> get() = mBasketProducts

    fun getHomeProducts(homeApi: String) {
        viewModelScope.launch {
            mHomeProducts.value = getHomeProductsUseCaseImpl.getHomeProducts(homeApi = homeApi)
        }
    }

    fun getProductDetails(productDetailsApi: String) {
        viewModelScope.launch {
            mProductDetails.value = getProductDetailsUseCaseImpl.getProductDetails(productDetailApi = productDetailsApi)
        }
    }

    fun getBasketProducts(basketProductsApi: String) {
        viewModelScope.launch {
            mBasketProducts.value = getBasketProductsUseCaseImpl.getBasketProducts(basketProductsApi = basketProductsApi)
        }
    }

}