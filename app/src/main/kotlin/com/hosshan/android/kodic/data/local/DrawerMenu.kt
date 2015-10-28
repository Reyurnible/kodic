package com.hosshan.android.kodic.data.local

import android.support.annotation.StringRes
import com.hosshan.android.kodic.R

/**
 * Created by shunhosaka on 2015/10/29.
 */
enum class DrawerMenu(@StringRes val title: Int) {
    Login(R.string.drawer_menu_login),
    Licence(R.string.drawer_menu_license)
    ;
}