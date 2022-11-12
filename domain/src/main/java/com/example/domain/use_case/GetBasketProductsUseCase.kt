package com.example.domain.use_case

import com.example.domain.model.basket.BasketModel
import com.example.domain.model.network_result.NetworkResult

interface GetBasketProductsUseCase {

    suspend fun getBasketProducts(
        basketProductsApi: String
    ): NetworkResult<BasketModel>

}