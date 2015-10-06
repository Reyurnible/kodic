package com.hosshan.android.kodic.component.fragment.project

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import dagger.Component

/**
 * Created by shunhosaka on 15/10/03.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ProjectModule::class))
interface ProjectComponent {

    fun inject(fragment: ProjectListFragment)

    object Initializer {

        fun init(activity: Activity): ProjectComponent =
                DaggerProjectComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .projectModule(ProjectModule())
                        .build()

    }


}