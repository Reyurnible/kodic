package com.hosshan.android.godicparents.store.adapter

import android.content.Context
import com.hosshan.android.godicparents.CodicService
import com.hosshan.android.godicparents.model.TranslatedText
import com.hosshan.android.godicparents.util.CodicRetrofitUtil
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