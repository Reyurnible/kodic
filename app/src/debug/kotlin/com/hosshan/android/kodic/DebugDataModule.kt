package com.hosshan.android.kodic


/**
 * Created by shunhosaka on 15/10/02.
 */
@Module(includes = arrayOf(ApiModule::class, StoreModule::class))
public class DebugDataModule {

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
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRequestInterceptor(): RequestInterceptor =
            RequestInterceptor {
                it.addHeader("Authorization", "Bearer " + "bHEdExeRcKaUS8nnYhDbRsLPUuEN1FPV2");
            }

    @Provides
    fun provideRestAdapter(@Named("Api") client: OkHttpClient, endpoint: Endpoint, gson: Gson, requestInterceptor: RequestInterceptor): RestAdapter =
            RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(OkClient(client))
                    .setEndpoint(endpoint)
                    .setConverter(GsonConverter(gson))
                    .setRequestInterceptor(requestInterceptor)
                    .build()

    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences("kodic_debug", Context.MODE_PRIVATE)

}