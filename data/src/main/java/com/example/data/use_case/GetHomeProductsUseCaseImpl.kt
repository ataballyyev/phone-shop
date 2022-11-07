package com.example.data.use_case

import com.example.data.repository.NetworkRepositoryImpl
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.use_case.GetHomeProductsUseCase
import javax.inject.Inject

class GetHomeProductsUseCaseImpl @Inject constructor(
    private val repositoryImpl: NetworkRepositoryImpl
): GetHomeProductsUseCase {

    override suspend fun getHomeProducts(homeApi: String): NetworkResult<HomeModel> {
        return repositoryImpl.getHomeProducts(homeApi = homeApi)
    }

}