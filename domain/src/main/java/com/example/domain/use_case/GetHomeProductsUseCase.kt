package com.example.domain.use_case

import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult

interface GetHomeProductsUseCase {

    suspend fun getHomeProducts(
        homeApi: String
    ): NetworkResult<HomeModel>

}