package com.hosshan.android.kodic.store.local

import android.content.SharedPreferences
import com.hosshan.android.kodic.data.local.Token
import com.hosshan.android.kodic.util.GsonUtil
import rx.Observable

/**
 * Created by shunhosaka on 2015/10/28.
 */
class TokenStore(val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_TOKEN: String = "key_token"
    }

    fun getToken(): Observable<Token?> {
        val token = sharedPreferences.getString(KEY_TOKEN, null)
        token?.let {
            return Observable.just(GsonUtil.getInstance().fromJson(it, Token::class.java))
        }
        return Observable.just(null)
    }

    fun setToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).commit()
    }

}