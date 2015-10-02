package com.hosshan.android.kodic

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hosshan.android.kodic.store.StoreModule
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import retrofit.Endpoint
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import javax.inject.Named


/**
 * Created by shunhosaka on 15/10/02.
 */
@Module(includes = arrayOf(ApiModule::class, StoreModule::class))
public class ReleaseDataModule {

    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences("kodic_debug", Context.MODE_PRIVATE)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    public fun provideOkHttpClient(app: Application): OkHttpClient {
        val client = OkHttpClient()
        return client
    }

    @Provides
    @Named("Api")
    fun provideApiClient(client: OkHttpClient): OkHttpClient = client.clone()

    @Provides
    fun provideRestAdapter(endpoint: Endpoint, @Named("Api") client: OkHttpClient, gson: Gson): RestAdapter =
            RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.NONE)
                    .setClient(OkClient(client))
                    .setEndpoint(endpoint)
                    .setConverter(GsonConverter(gson))
                    .build()


}