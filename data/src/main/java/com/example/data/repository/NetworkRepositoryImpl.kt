package com.example.data.repository

import android.util.Log
import com.example.data.service.PhoneService
import com.example.domain.model.basket.BasketModel
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
import com.example.domain.model.product_detail_model.ProductDetailModel
import com.example.domain.repository.NetworkRepository
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): NetworkRepository {

    override suspend fun getHomeProducts(homeApi: String): NetworkResult<HomeModel> {
        return try {
            val response = retrofit
                .create(PhoneService::class.java)
                .getHomeProducts(home = homeApi)
            if (response.code() == 200) {
                Log.i("TAG", "RESPONSE SUCCESS")
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
            NetworkResult.Error(e.message)
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error(e.message)
        }
    }

    override suspend fun getProductDetails(productDetailApi: String): NetworkResult<ProductDetailModel> {
        return try {
            val response = retrofit
                .create(PhoneService::class.java)
                .getProductDetails(productDetailApi = productDetailApi)
            if (response.code() == 200) {
                Log.i("TAG", "RESPONSE SUCCESS")
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
            NetworkResult.Error(e.message)
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error(e.message)
        }
    }

    override suspend fun getBasketProducts(basketProductsApi: String): NetworkResult<BasketModel> {
        return try {
            val response = retrofit
                .create(PhoneService::class.java)
                .getBasketProducts(basketProductsApi = basketProductsApi)
            if (response.code() == 200) {
                Log.i("TAG", "RESPONSE SUCCESS")
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
            NetworkResult.Error(e.message)
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error(e.message)
        }
    }

}