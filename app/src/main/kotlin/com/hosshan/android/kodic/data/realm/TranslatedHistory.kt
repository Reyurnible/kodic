package com.hosshan.android.kodic.data.realm

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by shunhosaka on 15/10/06.
 */
@RealmClass
public open class TranslatedHistory(
        public open var createdAt: Date = Date(),
        public open var translatedText: String? = null
) : RealmObject() {}