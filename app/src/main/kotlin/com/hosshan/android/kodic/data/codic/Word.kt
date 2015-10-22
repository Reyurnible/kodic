package com.hosshan.android.kodic.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by shunhosaka on 2015/09/10.
 */
@RealmClass
public open class Word(
        public open var successful: Boolean = false,
        public open var text: String = "",
        @SerializedName("translated_text")
        public open var translatedText: String = "",
        @Ignore
        public open var candidates: List<Candidate> = ArrayList<Candidate>()
) : RealmObject()