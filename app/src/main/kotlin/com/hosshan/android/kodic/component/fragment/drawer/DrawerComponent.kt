package com.hosshan.android.kodic.component.fragment.drawer

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import dagger.Component

/**
 * Created by shunhosaka on 2015/10/29.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(DrawerModule::class))
interface DrawerComponent {

    fun inject(fragment: DrawerFragment)

    object Initializer {

        fun init(activity: Activity): DrawerComponent =
                DaggerDrawerComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .drawerModule(DrawerModule())
                        .build()

    }

}