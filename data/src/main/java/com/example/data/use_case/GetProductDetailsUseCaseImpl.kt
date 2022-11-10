package com.example.data.use_case

import com.example.data.repository.NetworkRepositoryImpl
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel
import com.example.domain.use_case.GetHomeProductsUseCase
import com.example.domain.use_case.GetProductDetailsUseCase
import javax.inject.Inject

class GetProductDetailsUseCaseImpl @Inject constructor(
    private val repositoryImpl: NetworkRepositoryImpl
): GetProductDetailsUseCase {

    override suspend fun getProductDetails(productDetailApi: String): NetworkResult<ProductDetailModel> {
        return repositoryImpl.getProductDetails(productDetailApi = productDetailApi)
    }

}