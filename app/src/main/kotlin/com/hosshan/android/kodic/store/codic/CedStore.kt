package com.hosshan.android.kodic.store.codic

import com.hosshan.android.kodic.model.CedEntry
import com.hosshan.android.kodic.model.CedTitle
import com.hosshan.android.kodic.store.service.CodicCedService
import rx.Observable

/**
 * Created by shunhosaka on 15/09/16.
 */
public class CedStore(val service: CodicCedService) {

    fun getCedLookup(query: String, number: Int = 10): Observable<List<CedTitle>> =
            service.getCedLookup(query, number)

    fun getCedEntry(id: Int): Observable<CedEntry> =
            service.getCedEntry(id)

}