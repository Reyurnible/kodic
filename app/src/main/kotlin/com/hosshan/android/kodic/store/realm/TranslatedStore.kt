package com.hosshan.android.kodic.store.realm

import com.hosshan.android.kodic.data.realm.TranslatedHistory
import com.hosshan.android.kodic.model.TranslatedText
import io.realm.Realm
import java.util.*

/**
 * Created by shunhosaka on 15/10/06.
 */
public class TranslatedStore(val realm: Realm) {

    fun getTranslatedHistory() = realm.where(TranslatedHistory::class.java).findAll()

    fun saveTranslatedHistory(translatedText: TranslatedText) {
        realm.use {
            it.executeTransaction {
                val translatedHistory = TranslatedHistory(Date(), translatedText)
                realm.copyToRealm(translatedHistory)
            }
        }
    }

}