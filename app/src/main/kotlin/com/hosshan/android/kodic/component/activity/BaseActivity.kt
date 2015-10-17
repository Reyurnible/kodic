package com.hosshan.android.kodic.component.activity

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by shunhosaka on 15/09/07.
 */
public open class BaseActivity : AppCompatActivity() {

    protected  fun setContentFragment(@IdRes resourceId: Int, fragment: Fragment) {
        supportFragmentManager?.beginTransaction()?.replace(resourceId, fragment)?.commit()
    }

    protected fun addContentFragment(@IdRes resourceId: Int, fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager?.beginTransaction()?.add(resourceId, it)?.commit()
        }
    }

    public fun getContentFragment(@IdRes resourceId: Int): Fragment? {
        return supportFragmentManager.findFragmentById(resourceId)
    }

    protected fun getContentFragment(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    protected fun addFragment(tag: String, fragment: Fragment) {
        supportFragmentManager?.beginTransaction()?.add(fragment, tag)?.commit()
    }

    fun showDetails<T : Fragment>(@StringRes title: Int, fragmentClass: Class<T>, args: Bundle?) {
        showDetails(getString(title), fragmentClass, args)
    }

    fun showDetails<T : Fragment>(title: CharSequence?, fragmentClass: Class<T>, args: Bundle?) {
        startActivity(DetailsActivity.createIntent(applicationContext, title, fragmentClass, args))
    }

    fun showDetailsForResult<T : Fragment>(@StringRes title: Int, fragmentClass: Class<T>, args: Bundle?, requestCode: Int) {
        showDetailsForResult(getString(title), fragmentClass, args, requestCode)
    }

    fun showDetailsForResult<T : Fragment>(title: CharSequence?, fragmentClass: Class<T>, args: Bundle?, requestCode: Int) {
        startActivityForResult(DetailsActivity.createIntent(applicationContext, title, fragmentClass, args), requestCode)
    }


}