package com.hosshan.android.kodic

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
/**
 * Created by shunhosaka on 15/10/02.
 */
@Module
public class DebugAppModule(private val app: DaggerApplication) {

    @Provides
    fun provideApplication(): Application = app

    @Provides
    public fun provideRefWatcher(application: Application): RefWatcher =
            LeakCanary.install(application)


}