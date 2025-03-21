package com.almax.dagger2todaggerhilt.di.module

import android.app.Application
import android.content.Context
import com.almax.dagger2todaggerhilt.data.remote.NetworkService
import com.almax.dagger2todaggerhilt.di.ApplicationContext
import com.almax.dagger2todaggerhilt.di.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(
    private val application: Application
) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context =
        application

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String =
        "https://api.giphy.com/v1/gifs/"

    @Provides
    fun provideGsonConverter(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NetworkService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
}