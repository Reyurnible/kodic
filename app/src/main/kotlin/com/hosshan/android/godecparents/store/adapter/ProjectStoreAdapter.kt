package com.hosshan.android.godecparents.store

import android.content.Context
import com.hosshan.android.godecparents.CodicService
import com.hosshan.android.godecparents.model.Project
import com.hosshan.android.godecparents.util.CodicRetrofitUtil
import rx.Observable
import java.util.*
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectStoreAdapter {

    companion object {
        platformStatic fun getProject(context: Context, id: Int): Observable<Project?> {
            return CodicRetrofitUtil
                    .buildRestAdapter(context)
                    .create(javaClass<CodicService>())
                    .getProject(id)
        }

        platformStatic fun getProjectList(context: Context): Observable<List<Project>> {
            return CodicRetrofitUtil
                    .buildRestAdapter(context)
                    .create(javaClass<CodicService>())
                    .getProjectList()
        }
    }

}