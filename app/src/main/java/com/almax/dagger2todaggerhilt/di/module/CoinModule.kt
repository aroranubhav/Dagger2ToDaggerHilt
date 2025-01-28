package com.almax.dagger2todaggerhilt.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.almax.dagger2todaggerhilt.data.repository.CoinRepository
import com.almax.dagger2todaggerhilt.di.ActivityContext
import com.almax.dagger2todaggerhilt.ui.base.ViewModelProviderFactory
import com.almax.dagger2todaggerhilt.ui.coin.MainAdapter
import com.almax.dagger2todaggerhilt.ui.coin.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class CoinModule(
    private val activity: AppCompatActivity
) {

    @ActivityContext
    @Provides
    fun provideContext(): Context =
        activity

    @Provides
    fun provideMainViewModel(
        repository: CoinRepository
    ): MainViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(MainViewModel::class) {
                MainViewModel(repository)
            })[MainViewModel::class.java]
    }

    @Provides
    fun provideMainAdapter(): MainAdapter {
        return MainAdapter(arrayListOf())
    }
}