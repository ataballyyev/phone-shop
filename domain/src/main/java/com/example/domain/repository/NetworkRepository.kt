package com.example.domain.repository

import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult

interface NetworkRepository {

    suspend fun getHomeProducts(
        homeApi: String
    ): NetworkResult<HomeModel>


}