package com.hosshan.android.kodic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by shunhosaka on 2015/09/10.
 */
public data class Word(
        val successful: Boolean,
        val text: String,
        @SerializedName("translated_text")
        val translatedText: String,
        val candidates: List<Candidate> = ArrayList<Candidate>()
)