package com.hosshan.android.kodic.component.fragment.project

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import com.hosshan.android.kodic.store.codic.UserStore
import dagger.Component

/**
 * Created by shunhosaka on 15/10/03.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ProjectModule::class))
public interface ProjectComponent {

    public fun inject(fragment: ProjectListFragment)

    public object Initializer {

        public fun init(activity: Activity): ProjectComponent {
            return DaggerProjectComponent.builder()
                    .appComponent((activity.application as DaggerApplication).appComponent)
                    .projectModule(ProjectModule())
                    .build()
        }
    }


}