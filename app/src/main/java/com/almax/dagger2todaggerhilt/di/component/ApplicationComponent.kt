package com.almax.dagger2todaggerhilt.di.component

import com.almax.dagger2todaggerhilt.CoinApplication
import com.almax.dagger2todaggerhilt.data.remote.NetworkService
import com.almax.dagger2todaggerhilt.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: CoinApplication)

    fun getNetworkService(): NetworkService
}