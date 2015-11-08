package com.hosshan.android.kodic.store.codic

import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.activity.DetailsActivity
import com.hosshan.android.kodic.component.fragment.BaseFragment
import com.hosshan.android.kodic.component.fragment.login.LoginFragment
import retrofit.RetrofitError
import rx.Observer
import timber.log.Timber

/**
 * Created by shunhosaka on 2015/10/16.
 */
public open class CodicRequestSubscriber<T> : Observer<T> {

    val context: Context
    val fragment: BaseFragment?

    constructor(context: Context) : super() {
        this.context = context
        this.fragment = null
    }

    constructor(fragment: BaseFragment) : super() {
        this.context = fragment.activity
        this.fragment = fragment
    }

    override fun onCompleted() {
        Timber.d("onCompleted")
    }

    override fun onError(error: Throwable?) {
        Timber.d("onError:" + error?.toString())
        error ?: return
        if (error is RetrofitError) {
            when (error.response.status) {
                401 -> {
                    // Request token error
                    showLogin()
                }
                else -> {
                    // Show Error
                    showMessage(R.string.app_request_error)
                }
            }
        }
    }

    override fun onNext(t: T?) {
        Timber.d("onNext:" + t?.toString())
    }

    protected fun showMessage(@StringRes message: Int): Snackbar? {
        return showMessage(context.getString(message))
    }

    protected fun showMessage(message: String): Snackbar? {
        fragment?.let {
            val snackBar = Snackbar.make(it.view, R.string.app_request_error, Snackbar.LENGTH_SHORT)
            snackBar.show()
            return snackBar
        }
        return null
    }

    protected fun showLogin() {
        try {
            context.startActivity(DetailsActivity.createIntent(
                    context,
                    "",
                    LoginFragment::class.java,
                    LoginFragment.createArgument()))
        } catch(e: IllegalStateException) {
        } catch(e: IllegalThreadStateException) {
        }
    }

}
