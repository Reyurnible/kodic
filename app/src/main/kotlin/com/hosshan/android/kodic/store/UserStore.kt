package com.hosshan.android.kodic.store

import android.content.Context
import com.hosshan.android.kodic.CodicEngineService
import com.hosshan.android.kodic.model.Project
import com.hosshan.android.kodic.store.service.CodicUserService
import com.hosshan.android.kodic.util.CodicRetrofitUtil
import rx.Observable
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/12.
 */
public class UserStore(val service: CodicUserService) {

    fun getProject(id: Int): Observable<Project?> =
            service.getProject(id)

    fun getProjectList(context: Context): Observable<List<Project>> =
            service.getProjectList()

}