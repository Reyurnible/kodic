package com.hosshan.android.kodic.store.service

import com.hosshan.android.kodic.model.Project
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by shunhosaka on 15/10/02.
 */
public interface CodicUserService {

    @GET("/v1/user_projects.json")
    fun getProjectList(): Observable<List<Project>>

    @GET("/v1/user_projects/{id}.json")
    fun getProject(@Path("id") id: Int): Observable<Project?>

}