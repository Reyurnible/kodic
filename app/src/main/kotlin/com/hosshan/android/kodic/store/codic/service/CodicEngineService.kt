package com.hosshan.android.kodic

import com.hosshan.android.kodic.model.CedEntry
import com.hosshan.android.kodic.model.CedTitle
import com.hosshan.android.kodic.model.Project
import com.hosshan.android.kodic.model.TranslatedText
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

/**
 * Created by a40875 on 2015/09/10.
 */
public interface CodicEngineService {

    @GET("/v1/engine/translate.json")
    fun getTranslate(@Query("text") text: String, @Query("project_id") projectId: Int, @Query("casing") casing: String, @Query("acronym_style") acronymStyle: String): Observable<List<TranslatedText>>

    @GET("/v1/engine/translate.json")
    fun getTranslate(@Query("text") text: String, @Query("project_id") projectId: Int): Observable<List<TranslatedText>>

    @POST("/v1/engine/translate.json")
    fun postTranslate(): Observable<List<TranslatedText>>


}