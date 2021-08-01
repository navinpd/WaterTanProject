package com.compose.waterfilter.common.dependencyinjection.activity

import android.content.Context
import com.compose.waterfilter.ui.screen.main.MainActivity
import com.compose.waterfilter.common.dependencyinjection.DiConstants.ACTIVITY_CONTEXT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {


    @Provides
    @Named(ACTIVITY_CONTEXT)
    fun provideActivityContext(activity: MainActivity) : Context = activity

}