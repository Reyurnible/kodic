package com.hosshan.android.kodic.store

import android.content.Context
import com.hosshan.android.kodic.CodicService
import com.hosshan.android.kodic.model.Project
import com.hosshan.android.kodic.util.CodicRetrofitUtil
import rx.Observable
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectStoreAdapter {

    companion object {
        platformStatic fun getProject(context: Context, id: Int): Observable<Project?>
                = CodicRetrofitUtil
                .buildRestAdapter(context)
                .create(javaClass<CodicService>())
                .getProject(id)

        platformStatic fun getProjectList(context: Context): Observable<List<Project>>
                = CodicRetrofitUtil
                .buildRestAdapter(context)
                .create(javaClass<CodicService>())
                .getProjectList()
    }

}