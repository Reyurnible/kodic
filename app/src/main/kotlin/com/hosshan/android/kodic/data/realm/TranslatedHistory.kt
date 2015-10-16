package com.hosshan.android.kodic.data.realm

import com.hosshan.android.kodic.model.TranslatedText
import com.hosshan.android.kodic.model.Word
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by shunhosaka on 15/10/06.
 */
@RealmClass
public data class TranslatedHistory(
        val projectId: Int = -1,
        val createdAt: Date = Date(),
        val translatedText: TranslatedText? = null
) : RealmObject()