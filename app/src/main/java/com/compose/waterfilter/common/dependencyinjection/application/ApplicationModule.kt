package com.compose.waterfilter.common.dependencyinjection.application

import android.app.Application
import android.content.Context
import com.compose.waterfilter.PlaceHolderDependency
import com.compose.waterfilter.common.dependencyinjection.DiConstants.APPLICATION_CONTEXT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Named(APPLICATION_CONTEXT)
    fun provideApplicationContext(application: Application) : Context = application

    @Provides
    @Singleton
    fun providePlaceHolderModule() : PlaceHolderDependency = PlaceHolderDependency()

}