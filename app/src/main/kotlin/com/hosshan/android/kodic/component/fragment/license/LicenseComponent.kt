package com.hosshan.android.kodic.component.fragment.license

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import dagger.Component

/**
 * Created by shunhosaka on 2015/10/17.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(LicenseModule::class))
interface LicenseComponent {


    object Initializer {

        fun init(activity: Activity): LicenseComponent =
                DaggerLicenseComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .licenseModule(LicenseModule())
                        .build()

    }

}