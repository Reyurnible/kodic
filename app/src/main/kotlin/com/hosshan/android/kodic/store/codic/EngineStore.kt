package com.hosshan.android.kodic.store.codic

import android.content.Context
import com.hosshan.android.kodic.CodicEngineService
import com.hosshan.android.kodic.model.TranslatedText
import com.hosshan.android.kodic.util.CodicRetrofitUtil
import rx.Observable

/**
 * Created by shunhosaka on 15/09/14.
 */
public class EngineStore(val service: CodicEngineService) {

    fun getTranslate(text: String, projectId: Int, casing: String, acronymStyle: String): Observable<List<TranslatedText>> =
            service.getTranslate(text, projectId, casing, acronymStyle)

    fun getTranslate(text: String, projectId: Int): Observable<List<TranslatedText>> =
            service.getTranslate(text, projectId)

}