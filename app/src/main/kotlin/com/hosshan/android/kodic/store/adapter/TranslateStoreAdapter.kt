package com.hosshan.android.kodic.store.adapter

import android.content.Context
import com.hosshan.android.kodic.CodicService
import com.hosshan.android.kodic.model.TranslatedText
import com.hosshan.android.kodic.util.CodicRetrofitUtil
import rx.Observable

/**
 * Created by shunhosaka on 15/09/14.
 */
public class TranslateStoreAdapter {

    companion object {
        @JvmStatic fun getTranslate(context: Context, text: String, projectId: Int, casing: String, acronymStyle: String): Observable<List<TranslatedText>>
                = CodicRetrofitUtil
                .buildRestAdapter(context)
                .create(CodicService::class.java)
                .getTranslate(text, projectId, casing, acronymStyle)

        @JvmStatic fun getTranslate(context: Context, text: String, projectId: Int): Observable<List<TranslatedText>>
                = CodicRetrofitUtil
                .buildRestAdapter(context)
                .create(CodicService::class.java)
                .getTranslate(text, projectId)
    }

}