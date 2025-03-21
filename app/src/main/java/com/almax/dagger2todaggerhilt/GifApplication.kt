package com.almax.dagger2todaggerhilt

import android.app.Application
import com.almax.dagger2todaggerhilt.di.component.ApplicationComponent
import com.almax.dagger2todaggerhilt.di.component.DaggerApplicationComponent
import com.almax.dagger2todaggerhilt.di.module.ApplicationModule

class GifApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this@GifApplication))
            .build()
        component.inject(this@GifApplication)
    }
}