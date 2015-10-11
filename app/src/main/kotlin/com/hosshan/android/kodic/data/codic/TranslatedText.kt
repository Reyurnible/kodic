package com.hosshan.android.kodic.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by a40875 on 2015/09/10.
 */
@RealmClass
public data class TranslatedText(
        val successful: Boolean,
        val text: String,
        @SerializedName("translated_text")
        val translatedText: String,
        val words: List<Word>
) : RealmObject()