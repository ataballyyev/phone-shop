package com.example.data.service

import com.example.domain.model.home.HomeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneService {

    @GET("/v3/{HOME_API}")
    suspend fun getHomeProducts(
        @Path("HOME_API") home: String
    ): Response<HomeModel>


}