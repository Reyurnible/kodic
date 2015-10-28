package com.hosshan.android.kodic.store

import android.content.SharedPreferences
import com.hosshan.android.kodic.CodicEngineService
import com.hosshan.android.kodic.store.codic.CedStore
import com.hosshan.android.kodic.store.codic.EngineStore
import com.hosshan.android.kodic.store.codic.UserStore
import com.hosshan.android.kodic.store.local.TokenStore
import com.hosshan.android.kodic.store.realm.TranslatedStore
import com.hosshan.android.kodic.store.service.CodicCedService
import com.hosshan.android.kodic.store.service.CodicUserService
import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Created by shunhosaka on 15/10/03.
 */
@Module
public class StoreModule {

    @Provides
    fun provideCedStore(codicCedService: CodicCedService): CedStore =
            CedStore(codicCedService)

    @Provides
    fun provideEngineStore(codicEngineService: CodicEngineService): EngineStore =
            EngineStore(codicEngineService)

    @Provides
    fun provideUserStore(codicUserService: CodicUserService): UserStore =
            UserStore(codicUserService)

    @Provides
    fun provideTranslatedStore(realm: Realm): TranslatedStore =
            TranslatedStore(realm)

    @Provides
    fun provideTokenStore(sharedPreferences: SharedPreferences): TokenStore =
            TokenStore(sharedPreferences)

}