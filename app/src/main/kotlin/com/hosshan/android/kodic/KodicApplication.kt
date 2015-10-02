package com.hosshan.android.kodic

import android.app.Application
import timber.log.Timber

/**
 * Created by shunhosaka on 15/09/12.
 */
public class KodicApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
