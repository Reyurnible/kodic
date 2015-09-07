package com.hosshan.android.godecparents.component.activity

import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.hosshan.android.godecparents.R
import com.hosshan.android.godecparents.component.fragment.TopFragment

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : BaseActivity() {

    val drawerLayout: DrawerLayout by bindView(R.id.main_drawerlayout)
    val navigationMenu: NavigationMenuView by bindView(R.id.main_navigation_menu)
    val toolbar: Toolbar by bindView(R.id.main_toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentFragment(R.id.main_layout_container, TopFragment.createFragment())
    }
}