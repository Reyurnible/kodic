package com.hosshan.android.godicparents.store.adapter

import android.content.Context
import com.hosshan.android.godicparents.CodicService
import com.hosshan.android.godicparents.model.CedEntry
import com.hosshan.android.godicparents.model.CedTitle
import com.hosshan.android.godicparents.util.CodicRetrofitUtil
import rx.Observable
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/16.
 */
public class CedStoreAdapter {

    companion object {
        @JvmStatic fun getCedLookup(context: Context, query: String, number: Int = 10): Observable<List<CedTitle>>
                = CodicRetrofitUtil
                .buildRestAdapter(context)
                .create(CodicService::class.java)
                .getCedLookup(query, number)

        @JvmStatic fun getCedEntry(context: Context, id: Int): Observable<CedEntry>
                = CodicRetrofitUtil
                .buildRestAdapter(context)
                .create(CodicService::class .java)
                .getCedEntry(id)
    }

}