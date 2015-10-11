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
public open class TranslatedHistory(
        public open var createdAt: Date = Date(),
        public open var successful: Boolean = false,
        public open var text: String = "",
        public open var translatedText: String = ""
) : RealmObject() {

}

public inline fun TranslatedHistory.toTranslatedText(): TranslatedText =
        TranslatedText(
                this.successful,
                this.text,
                this.translatedText,
                ArrayList<Word>()
        )

// Realmのオブジェクトが後にできて、できた依存関係なのでこちらに拡張関数として書く
public inline fun TranslatedText.toTranslatedHistory(): TranslatedHistory =
        TranslatedHistory(
                Date(),
                this.successful,
                this.text,
                this.translatedText
        )