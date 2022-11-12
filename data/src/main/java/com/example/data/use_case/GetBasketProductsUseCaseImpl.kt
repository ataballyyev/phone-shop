package com.example.data.use_case

import com.example.data.repository.NetworkRepositoryImpl
import com.example.domain.model.basket.BasketModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.use_case.GetBasketProductsUseCase
import javax.inject.Inject

class GetBasketProductsUseCaseImpl @Inject constructor(
    private val repositoryImpl: NetworkRepositoryImpl
): GetBasketProductsUseCase {

    override suspend fun getBasketProducts(basketProductsApi: String): NetworkResult<BasketModel> {
        return repositoryImpl.getBasketProducts(basketProductsApi = basketProductsApi)
    }

}