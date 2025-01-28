package com.almax.dagger2todaggerhilt.di.component

import com.almax.dagger2todaggerhilt.di.ActivityScope
import com.almax.dagger2todaggerhilt.di.module.CoinModule
import com.almax.dagger2todaggerhilt.ui.coin.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [CoinModule::class]
)
interface CoinComponent {

    fun inject(activity: MainActivity)
}