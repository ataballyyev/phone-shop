package com.example.phoneshop.presentation.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.use_case.GetHomeProductsUseCaseImpl
import com.example.data.use_case.GetProductDetailsUseCaseImpl
import com.example.phoneshop.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getHomeProductsUseCaseImpl: GetHomeProductsUseCaseImpl,
    private val getProductDetailsUseCaseImpl: GetProductDetailsUseCaseImpl
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getHomeProductsUseCaseImpl = getHomeProductsUseCaseImpl,
                getProductDetailsUseCaseImpl = getProductDetailsUseCaseImpl
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}