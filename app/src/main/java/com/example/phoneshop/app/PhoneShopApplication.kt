package com.example.phoneshop.app

import android.app.Application
import com.example.phoneshop.di.component.AppComponent
import com.example.phoneshop.di.component.DaggerAppComponent

class PhoneShopApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}