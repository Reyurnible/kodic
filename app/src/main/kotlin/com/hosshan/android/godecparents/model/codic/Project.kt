package com.hosshan.android.godecparents.model

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * Created by a40875 on 2015/09/10.
 */
data public class Project(
        val id: Int,
        val name: String,
        val description: String,
        @SerializedName("created_on") val createdOn: Date,
        @SerializedName("words_count") val wordsCount: Int,
        val owner: Owner
)