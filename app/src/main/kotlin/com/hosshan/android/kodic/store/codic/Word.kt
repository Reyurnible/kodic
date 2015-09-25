package com.hosshan.android.kodic.model

import com.google.gson.annotations.SerializedName

/**
 * Created by a40875 on 2015/09/10.
 */
data public class Word(
        val successful: Boolean,
        val text: String,
        @SerializedName("translated_text") val translatedText: String,
        val candidates: List<Candidate>
)