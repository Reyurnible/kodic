package com.hosshan.android.kodic

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.f2prateek.rx.preferences.RxSharedPreferences
import com.google.gson.Gson
import com.hosshan.android.kodic.data.local.Token
import com.hosshan.android.kodic.store.StoreModule
import com.hosshan.android.kodic.store.codic.service.ApiModule
import com.hosshan.android.kodic.store.local.TokenStore
import com.hosshan.android.kodic.util.GsonUtil
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit.Endpoint
import retrofit.Endpoints
import retrofit.RequestInterceptor
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import rx.Subscriber
import rx.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by shunhosaka on 15/10/02.
 */
@Module(includes = arrayOf(ApiModule::class, StoreModule::class))
public class ReleaseDataModule {

    companion object {
        // If your project want to change endpoint, dividing ApiModule.
        @JvmStatic val CODIC_API_URL: String = "https://api.codic.jp"
    }

    @Provides
    @Named("Api")
    fun provideApiClient(client: OkHttpClient): OkHttpClient = client.clone()

    @Provides
    public fun provideOkHttpClient(app: Application): OkHttpClient {
        val client = OkHttpClient()
        return client
    }

    @Provides
    fun provideEndpoint(): Endpoint = Endpoints.newFixedEndpoint(CODIC_API_URL)

    @Provides
    fun provideGson(): Gson = GsonUtil.getInstance()

    @Provides
    fun provideRequestInterceptor(tokenStore: TokenStore): RequestInterceptor = RequestInterceptor { request ->
        tokenStore.getToken()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Token?>() {
                    override fun onCompleted() { }
                    override fun onNext(token: Token?) {
                        token?.let {
                            request.addHeader("Authorization", "Bearer " + "");
                        }
                    }
                    override fun onError(e: Throwable?) { }
                });
    }

    @Provides
    fun provideRestAdapter(@Named("Api") client: OkHttpClient, endpoint: Endpoint, gson: Gson, requestInterceptor: RequestInterceptor): RestAdapter =
            RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.NONE)
                    .setClient(OkClient(client))
                    .setEndpoint(endpoint)
                    .setConverter(GsonConverter(gson))
                    .setRequestInterceptor(requestInterceptor)
                    .build()

    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences("kodic", Context.MODE_PRIVATE)

    @Provides
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences =
            RxSharedPreferences.create(sharedPreferences)

    @Provides
    fun provideRealm(app: Application): Realm =
            Realm.getInstance(RealmConfiguration.Builder(app)
                    .name("kodic")
                    .schemaVersion(1)
                    .build())
}
