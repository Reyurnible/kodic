package com.hosshan.android.kodic.data.realm

import com.hosshan.android.kodic.model.TranslatedText
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by shunhosaka on 15/10/06.
 */
@RealmClass
public open class TranslatedHistory(
        public open val createdAt: Date,
        public open val translatedText: String
) : RealmObject()