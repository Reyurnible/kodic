package com.hosshan.android.kodic.store.realm

import com.hosshan.android.kodic.data.realm.TranslatedHistory
import com.hosshan.android.kodic.data.realm.toTranslatedHistory
import com.hosshan.android.kodic.model.TranslatedText
import io.realm.Realm

/**
 * Created by shunhosaka on 15/10/06.
 */
public class TranslatedStore(val realm: Realm) {

    fun getTranslatedHistory() = realm.where(TranslatedHistory::class.java).findAll()

    fun saveTranslatedHistory(translatedText: TranslatedText) {
        realm.use {
            it.executeTransaction {
                val translatedHistory = translatedText.toTranslatedHistory()
                realm.copyToRealm(translatedHistory)
            }
        }
    }
}