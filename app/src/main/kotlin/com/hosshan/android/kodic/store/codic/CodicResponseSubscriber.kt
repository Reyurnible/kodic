package com.hosshan.android.kodic.store.codic

import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.hosshan.android.kodic.R
import retrofit.RetrofitError
import rx.Observer
import timber.log.Timber

/**
 * Created by shunhosaka on 2015/10/22.
 */
public class CodicResponseSubscriber<T> : Observer<T> {
    private val context: Context
    private val fragment: Fragment?

    constructor(context: Context) {
        this.context = context
        this.fragment = null
    }

    constructor(fragment: Fragment) {
        this.context = fragment.activity
        this.fragment = fragment
    }

    override fun onCompleted() {
        Timber.d("onCompleted")
    }

    override fun onError(e: Throwable?) {
        Timber.e("onError:" + e?.toString())
        e?.let {
            if (it is RetrofitError) {
                when (it.response.status) {
                // TODO Add response status message
                    else -> {
                        showMessage(R.string.app_request_error)
                    }
                }
            }
        }
    }

    override fun onNext(response: T?) {
        Timber.d("onNext:" + response?.toString())
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
}