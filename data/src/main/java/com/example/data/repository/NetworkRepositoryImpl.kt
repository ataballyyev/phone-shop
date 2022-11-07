package com.example.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.data.service.PhoneService
import com.example.domain.model.home.HomeModel
import com.example.domain.model.network_result.NetworkResult
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
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message)
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error(e.message)
        }
    }

}