package com.hosshan.android.godecparents.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit.converter.GsonConverter
import kotlin.platform.platformStatic

/**
 * Created by a40875 on 2015/09/10.
 */
public class GsonUtil {
    companion object {
        platformStatic fun getInstance(): Gson {
            val builder: GsonBuilder = GsonBuilder()
            builder.setDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
            return builder.create()
        }

        platformStatic fun getRetrofitConverter(): GsonConverter {
            return GsonConverter(getInstance())
        }
    }
}