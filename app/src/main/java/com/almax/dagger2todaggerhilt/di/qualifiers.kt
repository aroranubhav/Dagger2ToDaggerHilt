package com.almax.dagger2todaggerhilt.di

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class ActivityContext