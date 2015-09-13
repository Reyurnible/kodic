package com.hosshan.android.godicparents

import com.hosshan.android.godicparents.model.CedEntry
import com.hosshan.android.godicparents.model.CedTitle
import com.hosshan.android.godicparents.model.Project
import com.hosshan.android.godicparents.model.TranslatedText
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

/**
 * Created by a40875 on 2015/09/10.
 */
public interface CodicService {

    @GET("/v1/engine/translate.json")
    fun getTranslate(@Query("text") text: String): Observable<List<TranslatedText>>

    @POST("/v1/engine/translate.json")
    fun postTranslate(): Observable<List<TranslatedText>>

    @GET("/v1/user_projects.json")
    fun getProjectList(): Observable<List<Project>>

    @GET("/v1/user_projects/{id}.json")
    fun getProject(@Path("id") id: Int): Observable<Project?>

    @GET("/v1/ced/lookup.json")
    fun getCedLookup(@Query("query") query: String, @Query("count") number: Int = 10): Observable<List<CedTitle>>

    @GET("/v1/ced/entries/{id}.json")
    fun getCedEntry(@Path("id") id: Int): Observable<CedEntry>
}