package com.hosshan.android.godicparents.model

/**
 * Created by a40875 on 2015/09/10.
 */
data public class CedEntry(
        val id: Int,
        val title: String,
        val digest: String,
        val pronunciations: List<Pronunciation>,
        val translations: List<Translation>,
        val note: String
)