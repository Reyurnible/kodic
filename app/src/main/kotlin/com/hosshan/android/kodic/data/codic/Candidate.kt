package com.hosshan.android.kodic.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by shunhosaka on 2015/09/10.
 */
@RealmClass
public data class Candidate(
        val text: String
) : RealmObject()