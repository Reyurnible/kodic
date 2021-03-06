package com.hosshan.android.kodic

import android.graphics.Typeface
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import com.orhanobut.hawk.LogLevel
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/12.
 */
public class KodicApplication : DaggerApplication() {
    companion object {
        private const val CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf"
        public var canaroExtraBold: Typeface by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setPassword("kodic")
                .setLogLevel(if (BuildConfig.DEBUG) LogLevel.FULL else LogLevel.NONE)
                .buildRx()

        initTypeface()
    }

    private fun initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH)
    }
}
