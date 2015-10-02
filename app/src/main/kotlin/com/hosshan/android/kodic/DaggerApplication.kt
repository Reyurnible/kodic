package com.hosshan.android.kodic

import android.app.Application
import com.squareup.okhttp.OkHttpClient
import javax.inject.Inject

/**
 * Created by shunhosaka on 15/10/02.
 */
public open class DaggerApplication : Application() {

    val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this) }
    @Inject lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        ApplicationGlideModule.registerComponents(this, okHttpClient)
    }

}