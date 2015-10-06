package com.hosshan.android.kodic.component.fragment.translate

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import dagger.Component

/**
 * Created by shunhosaka on 15/10/03.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(TranslateModule::class))
interface TranslateComponent {

    fun inject(fragment: TranslateFragment)

    object Initializer {

        fun init(activity: Activity): TranslateComponent =
                DaggerTranslateComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .translateModule(TranslateModule())
                        .build()

    }


}