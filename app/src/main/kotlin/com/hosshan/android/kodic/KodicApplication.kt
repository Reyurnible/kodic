package com.hosshan.android.kodic

import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import com.orhanobut.hawk.LogLevel
import timber.log.Timber

/**
 * Created by shunhosaka on 15/09/12.
 */
public class KodicApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setPassword("kodic")
                .setLogLevel(if (BuildConfig.DEBUG) LogLevel.FULL else LogLevel.NONE)
                .build()
    }
}
