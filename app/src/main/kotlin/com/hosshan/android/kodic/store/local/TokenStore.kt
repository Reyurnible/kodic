package com.hosshan.android.kodic.store.local

import com.hosshan.android.kodic.data.local.Token
import com.orhanobut.hawk.Hawk
import rx.Observable

/**
 * Created by shunhosaka on 2015/10/28.
 */
class TokenStore() {

    fun getToken(): Observable<Token?> = Hawk.getObservable<Token?>(Token::class.java.simpleName)

    fun getTokenNonObservable(): Token? = Hawk.get(Token::class.java.simpleName)

    fun setToken(token: Token): Observable<Boolean> = Hawk.putObservable(Token::class.java.simpleName, token)

}
