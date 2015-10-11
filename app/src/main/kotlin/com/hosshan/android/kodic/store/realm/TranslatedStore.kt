package com.hosshan.android.kodic.store.realm

import com.hosshan.android.kodic.data.realm.TranslatedHistory
import com.hosshan.android.kodic.data.realm.toTranslatedHistory
import com.hosshan.android.kodic.model.TranslatedText
import io.realm.Realm

/**
 * Created by shunhosaka on 15/10/06.
 */
public class TranslatedStore(val realm: Realm) {

    fun getTranslatedHistory(projectId: Int) = realm.where(TranslatedHistory::class.java).equalTo("projectId", projectId).findAll()

    fun saveTranslatedHistory(projectId: Int, translatedText: TranslatedText) {
        realm.use {
            it.executeTransaction {
                val translatedHistory = translatedText.toTranslatedHistory(projectId)
                realm.copyToRealm(translatedHistory)
            }
        }
    }
}