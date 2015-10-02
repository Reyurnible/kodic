package com.hosshan.android.kodic.store.adapter

import android.content.Context
import com.hosshan.android.kodic.CodicEngineService
import com.hosshan.android.kodic.model.CedEntry
import com.hosshan.android.kodic.model.CedTitle
import com.hosshan.android.kodic.store.service.CodicCedService
import com.hosshan.android.kodic.util.CodicRetrofitUtil
import rx.Observable
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/16.
 */
public class CedStore(val service: CodicCedService) {

    fun getCedLookup(query: String, number: Int = 10): Observable<List<CedTitle>> =
            service.getCedLookup(query, number)

    fun getCedEntry(id: Int): Observable<CedEntry> =
            service.getCedEntry(id)

}