package com.hosshan.android.kodic.util

import com.cookpad.android.rxt4a.operators.OperatorAddToCompositeSubscription
import com.cookpad.android.rxt4a.subscriptions.AndroidCompositeSubscription
import rx.Observable

/**
 * Created by shunhosaka on 2015/10/11.
 */
public inline fun <T> Observable<T>.addComposite(compositeSubscription: AndroidCompositeSubscription) =
        this.lift(OperatorAddToCompositeSubscription<T>(compositeSubscription))
