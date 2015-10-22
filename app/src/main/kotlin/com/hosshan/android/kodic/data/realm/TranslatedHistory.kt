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
         public open var projectId: Int = -1,
         public open var createdAt: Date = Date(),
         // Translated Text Object
         public open var successful: Boolean = false,
         public open var text: String = "",
         public open var translatedText: String = ""
) : RealmObject()

public fun TranslatedHistory.toTranslatedText(): TranslatedText =
        TranslatedText(this.successful,
                this.text,
                this.translatedText,
                ArrayList())