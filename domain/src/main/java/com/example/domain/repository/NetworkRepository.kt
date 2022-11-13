package com.example.domain.repository

import com.example.domain.model.basket.BasketModel
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel

interface NetworkRepository {

    suspend fun getHomeProducts(
        homeApi: String
    ): NetworkResult<HomeModel>

    suspend fun getProductDetails(
        productDetailApi: String
    ): NetworkResult<ProductDetailModel>

    suspend fun getBasketProducts(
        basketProductsApi: String
    ): NetworkResult<BasketModel>

}