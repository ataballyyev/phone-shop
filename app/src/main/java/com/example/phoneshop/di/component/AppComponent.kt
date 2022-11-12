package com.example.phoneshop.di.component

import com.example.data.di.NetworkModule
import com.example.phoneshop.presentation.view.cart.CartFragment
import com.example.phoneshop.presentation.view.detail.DetailsFragment
import com.example.phoneshop.presentation.view.home.HomeFragment
import dagger.Component

@Component(
    modules = [NetworkModule::class]
)
interface AppComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: CartFragment)

}