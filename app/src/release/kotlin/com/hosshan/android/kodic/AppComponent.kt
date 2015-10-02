package com.hosshan.android.kodic

import dagger.Component

/**
 * Created by shunhosaka on 15/10/02.
 */
@Component(modules = arrayOf(ReleaseAppModule::class, ReleaseDataModule::class))
public interface AppComponent : BaseAppComponent {

    fun inject(app: DaggerApplication)

    object Initializer {
        fun init(app: DaggerApplication): AppComponent =
                DaggerAppComponent.builder()
                        .releaseAppModule(ReleaseAppModule(app))
                        .releaseDataModule(ReleaseDataModule())
                        .build()
    }

}