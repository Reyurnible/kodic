package com.hosshan.android.kodic

import android.app.Application
import com.hosshan.android.kodic.store.codic.CedStore
import com.hosshan.android.kodic.store.codic.EngineStore
import com.hosshan.android.kodic.store.codic.UserStore
import com.hosshan.android.kodic.store.local.TokenStore
import com.hosshan.android.kodic.store.realm.TranslatedStore

/**
 * Created by shunhosaka on 15/10/03.
 */
public interface  BaseAppComponent {

    public fun application(): Application

    public fun cedStore(): CedStore

    public fun engineStore(): EngineStore

    public fun userStore(): UserStore

    public fun translatedStore(): TranslatedStore

    public fun tokenStore(): TokenStore

}