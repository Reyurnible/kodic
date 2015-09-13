package com.hosshan.android.godicparents.store.adapter

import android.content.Context
import com.hosshan.android.godicparents.CodicService
import com.hosshan.android.godicparents.model.TranslatedText
import com.hosshan.android.godicparents.util.CodicRetrofitUtil
import rx.Observable
import kotlin.platform.platformStatic

/**
 * Created by shunhosaka on 15/09/14.
 */
public class TranslateStoreAdapter {

    companion object {
        platformStatic fun getTranslate(context: Context, text: String, projectId: Int, casing: String, acronymStyle: String): Observable<List<TranslatedText>> {
            return CodicRetrofitUtil
                    .buildRestAdapter(context)
                    .create(javaClass<CodicService>())
                    .getTranslate(text, projectId, casing, acronymStyle)
        }

        platformStatic fun getTranslate(context: Context, text: String, projectId: Int): Observable<List<TranslatedText>> {
            return CodicRetrofitUtil
                    .buildRestAdapter(context)
                    .create(javaClass<CodicService>())
                    .getTranslate(text, projectId)
        }
    }

}