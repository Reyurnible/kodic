package com.hosshan.android.kodic.store.service

import com.hosshan.android.kodic.model.CedEntry
import com.hosshan.android.kodic.model.CedTitle
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

/**
 * Created by shunhosaka on 15/10/02.
 */
public interface CodicCedService {

    @GET("/v1/ced/lookup.json")
    fun getCedLookup(@Query("query") query: String, @Query("count") number: Int = 10): Observable<List<CedTitle>>

    @GET("/v1/ced/entries/{id}.json")
    fun getCedEntry(@Path("id") id: Int): Observable<CedEntry>

}