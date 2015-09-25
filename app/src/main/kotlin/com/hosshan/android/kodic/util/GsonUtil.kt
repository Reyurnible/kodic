package com.hosshan.android.kodic.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit.converter.GsonConverter
import kotlin.platform.platformStatic

/**
 * Created by a40875 on 2015/09/10.
 */
public class GsonUtil {
    companion object {
        @JvmStatic fun getInstance(): Gson {
            val builder: GsonBuilder = GsonBuilder()
            builder.setDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
            return builder.create()
        }

        @JvmStatic fun getRetrofitConverter(): GsonConverter {
            return GsonConverter(getInstance())
        }
    }
}