package com.hosshan.android.kodic.component.activity

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by shunhosaka on 15/09/07.
 */
public open class BaseActivity : AppCompatActivity() {

    fun setContentFragment(IdRes resourceId: Int, fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager?.beginTransaction()?.replace(resourceId, fragment)?.commit()
        }
    }

    fun addContentFragment(IdRes resourceId: Int, fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager?.beginTransaction()?.add(resourceId, fragment)?.commit()
        }
    }

    fun getContentFragment(IdRes resourceId: Int): Fragment? {
        return supportFragmentManager.findFragmentById(resourceId)
    }

    fun getContentFragment(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    fun addFragment(tag: String, fragment: Fragment) {
        fragment?.let {
            supportFragmentManager?.beginTransaction()?.add(fragment, tag)?.commit()
        }
    }

}