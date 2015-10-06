package com.hosshan.android.kodic.component.fragment.login

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import dagger.Component

/**
 * Created by shunhosaka on 15/10/06.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(LoginModule::class))
interface LoginComponent {

    fun inject(fragment: LoginFragment)

    object Initializer {

        fun init(activity: Activity): LoginComponent {
            return DaggerLoginComponent.builder()
                    .appComponent((activity.application as DaggerApplication).appComponent)
                    .loginModule(LoginModule())
                    .build()
        }
    }

}