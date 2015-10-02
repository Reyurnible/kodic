package com.hosshan.android.kodic

import android.app.Application
import dagger.Module
import dagger.Provides
/**
 * Created by shunhosaka on 15/10/02.
 */
@Module
public class ReleaseAppModule(private val app: DaggerApplication) {

    @Provides
    fun provideApplication(): Application = app

}