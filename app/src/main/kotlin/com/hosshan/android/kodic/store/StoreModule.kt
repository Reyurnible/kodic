package com.hosshan.android.kodic.store

import com.hosshan.android.kodic.CodicEngineService
import com.hosshan.android.kodic.store.codic.CedStore
import com.hosshan.android.kodic.store.codic.EngineStore
import com.hosshan.android.kodic.store.codic.UserStore
import com.hosshan.android.kodic.store.service.CodicCedService
import com.hosshan.android.kodic.store.service.CodicUserService
import dagger.Module
import dagger.Provides

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
}