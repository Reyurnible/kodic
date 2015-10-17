package com.hosshan.android.kodic.store.codic

import android.content.Context
import android.support.design.widget.Snackbar
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.BaseFragment
import retrofit.RetrofitError
import rx.Subscriber
import timber.log.Timber

/**
 * Created by shunhosaka on 2015/10/16.
 */
public open class CodicRequestSubscriber<T> : Subscriber<T> {

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
        error ?: return
        Timber.d("onError:" + error.toString())
        if (error is RetrofitError) {
            when (error.response.status) {
                401 -> {
                    // Request token error
                }
                else -> {
                    // Show Error
                    fragment?.let {
                        Snackbar.make(it.view, R.string.app_request_error, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onNext(t: T?) {
        Timber.d("onNext:" + t.toString())

    }

}