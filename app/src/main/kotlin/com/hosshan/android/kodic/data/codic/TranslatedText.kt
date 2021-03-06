package com.hosshan.android.kodic.model

import com.google.gson.annotations.SerializedName

/**
 * Created by shunhosaka on 2015/09/10.
 */
public data class TranslatedText(
        val successful: Boolean,
        val text: String,
        @SerializedName("translated_text")
        val translatedText: String,
        val words: List<Word>
)