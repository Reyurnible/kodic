package com.hosshan.android.kodic.store.codic

import com.hosshan.android.kodic.model.Project
import com.hosshan.android.kodic.store.service.CodicUserService
import rx.Observable

/**
 * Created by shunhosaka on 15/09/12.
 */
public class UserStore(val service: CodicUserService) {

    fun getProject(id: Int): Observable<Project?> =
            service.getProject(id)

    fun getProjectList(): Observable<List<Project>> =
            service.getProjectList()

}