package com.hosshan.android.kodic.component.fragment.translate

import android.app.Activity
import com.hosshan.android.kodic.AppComponent
import com.hosshan.android.kodic.DaggerApplication
import dagger.Component

/**
 * Created by shunhosaka on 15/10/03.
 */
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(TranslateModule::class))
public interface TranslateComponent {

    public fun inject(fragment: TranslateFragment)

    public object Initializer {

        public fun init(activity: Activity): TranslateComponent {
            return DaggerTranslateComponent.builder()
                    .appComponent((activity.application as DaggerApplication).appComponent)
                    .translateModule(TranslateModule())
                    .build()
        }
    }


}