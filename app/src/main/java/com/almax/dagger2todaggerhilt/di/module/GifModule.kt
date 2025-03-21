package com.almax.dagger2todaggerhilt.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.almax.dagger2todaggerhilt.data.repository.TrendingGifRepository
import com.almax.dagger2todaggerhilt.di.ApplicationContext
import com.almax.dagger2todaggerhilt.presentation.base.ViewModelProviderFactory
import com.almax.dagger2todaggerhilt.presentation.gif.GifAdapter
import com.almax.dagger2todaggerhilt.presentation.gif.GifViewModel
import dagger.Module
import dagger.Provides

@Module
class GifModule(
    private val activity: AppCompatActivity
) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context =
        activity

    @Provides
    fun provideGifViewModel(
        repository: TrendingGifRepository
    ): GifViewModel =
        ViewModelProvider(
            activity,
            ViewModelProviderFactory(GifViewModel::class) {
                GifViewModel(repository)
            })[GifViewModel::class]

    @Provides
    fun provideGifAdapter(): GifAdapter =
        GifAdapter(arrayListOf())
}