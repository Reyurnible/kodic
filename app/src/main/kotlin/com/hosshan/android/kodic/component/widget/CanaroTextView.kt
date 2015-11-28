package com.hosshan.android.kodic.component.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

import com.hosshan.android.kodic.KodicApplication

/**
 * Created by Dmytro Denysenko on 5/6/15.
 */
class CanaroTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextView(context, attrs, defStyleAttr) {

    init {
        typeface = KodicApplication.canaroExtraBold
    }

}
