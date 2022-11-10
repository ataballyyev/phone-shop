package com.example.domain.use_case

import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel

interface GetProductDetailsUseCase {

    suspend fun getProductDetails(
        productDetailApi: String
    ): NetworkResult<ProductDetailModel>

}