package com.example.data.service

import com.example.domain.model.basket.BasketModel
import com.example.domain.model.home.HomeModel
import com.example.domain.model.product_detail_model.ProductDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneService {

    @GET("/v3/{HOME_API}")
    suspend fun getHomeProducts(
        @Path("HOME_API") home: String
    ): Response<HomeModel>

    @GET("/v3/{PRODUCT_DETAIL_API}")
    suspend fun getProductDetails(
        @Path("PRODUCT_DETAIL_API") productDetailApi: String
    ): Response<ProductDetailModel>

    @GET("/v3/{BASKET_PRODUCTS_API}")
    suspend fun getBasketProducts(
        @Path("BASKET_PRODUCTS_API") basketProductsApi: String
    ): Response<BasketModel>

}