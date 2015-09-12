package com.hosshan.android.godecparents

import android.app.Application
import timber.log.Timber

/**
 * Created by shunhosaka on 15/09/12.
 */
public class GodicParentsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}