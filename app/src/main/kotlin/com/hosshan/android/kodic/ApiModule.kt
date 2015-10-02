package com.hosshan.android.kodic

import com.hosshan.android.kodic.store.service.CodicCedService
import com.hosshan.android.kodic.store.service.CodicUserService
import dagger.Module
import dagger.Provides
import retrofit.Endpoint
import retrofit.Endpoints
import retrofit.RestAdapter

/**
 * Created by shunhosaka on 15/10/03.
 */
@Module
public class ApiModule {

    companion object {
        @JvmStatic val CODIC_API_URL: String = "https://api.codic.jp"
    }

    @Provides
    fun provideEndpoint(): Endpoint = Endpoints.newFixedEndpoint(CODIC_API_URL)

    @Provides
    fun provideCodicCedService(restAdapter: RestAdapter): CodicCedService =
            restAdapter.create(CodicCedService::class.java)

    @Provides
    fun provideCodicEngineService(restAdapter: RestAdapter): CodicEngineService =
            restAdapter.create(CodicEngineService::class.java)

    @Provides
    fun provideCodicUserService(restAdapter: RestAdapter): CodicUserService =
            restAdapter.create(CodicUserService::class.java)


}