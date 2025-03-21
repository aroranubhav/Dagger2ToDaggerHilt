package com.almax.dagger2todaggerhilt.di.component

import com.almax.dagger2todaggerhilt.di.ActivityScope
import com.almax.dagger2todaggerhilt.di.module.GifModule
import com.almax.dagger2todaggerhilt.presentation.gif.GifActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [GifModule::class]
)
interface GifComponent {

    fun inject(activity: GifActivity)
}