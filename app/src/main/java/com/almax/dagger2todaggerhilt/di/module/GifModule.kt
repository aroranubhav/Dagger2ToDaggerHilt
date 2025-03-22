package com.almax.dagger2todaggerhilt.di.module

import com.almax.dagger2todaggerhilt.presentation.gif.GifAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class GifModule {

    @ActivityScoped
    @Provides
    fun provideGifAdapter(): GifAdapter =
        GifAdapter(arrayListOf())
}