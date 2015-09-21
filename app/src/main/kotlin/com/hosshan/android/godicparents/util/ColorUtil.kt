package com.hosshan.android.godicparents.util

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.ColorInt
import com.hosshan.android.godicparents.R

/**
 * Created by shunhosaka on 15/09/19.
 */
public class ColorUtil {

    companion object {

        @JvmStatic
        @ColorInt
        fun getMaterialColor(context: Context, index: Int): Int {
            val colorArray: TypedArray = context.resources.obtainTypedArray(R.array.material_colors)
            return colorArray.getColor(index, context.getColor(R.color.primary))
        }

        @JvmStatic
        @ColorInt
        fun getMaterialDarkColor(context: Context, index: Int): Int {
            val colorArray: TypedArray = context.resources.obtainTypedArray(R.array.material_colors_dark)
            return colorArray.getColor(index, context.getColor(R.color.primary_dark))
        }

    }

}