package com.hosshan.android.kodic.store.adapter

import android.content.Context
import com.hosshan.android.kodic.CodicService
import com.hosshan.android.kodic.model.CedEntry
import com.hosshan.android.kodic.model.CedTitle
import com.hosshan.android.kodic.util.CodicRetrofitUtil
import rx.Observable
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/16.
 */
public class CedStore {

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