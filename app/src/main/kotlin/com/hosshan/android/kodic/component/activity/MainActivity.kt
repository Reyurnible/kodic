package com.hosshan.android.kodic.component.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.license.LicenseFragment
import com.hosshan.android.kodic.component.fragment.login.LoginFragment
import com.hosshan.android.kodic.component.fragment.project.ProjectListFragment
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : BaseActivity() {

    val drawerLayout: DrawerLayout by bindView(R.id.main_drawerlayout)
    val navigationView: NavigationView by bindView(R.id.main_navigation_view)
    val toolbar: Toolbar by bindView(R.id.main_toolbar)

    var drawerToggle: ActionBarDrawerToggle by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting Toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_inverse))
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START) else drawerLayout.openDrawer(GravityCompat.START)
        }

        drawerToggle = ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close)

        // Setting NavigationDrawer
        drawerLayout.setDrawerListener(drawerToggle)

        navigationView.setNavigationItemSelectedListener { item: MenuItem? ->
            item?.let {
                when (it.itemId) {
                    R.id.menu_login -> LoginFragment.newInstance()
                    R.id.menu_license -> LicenseFragment.newInstance()
                    else -> null
                }?.let {
                    showDetails(item.title, it.javaClass, it.arguments)
                }
            }
            true
        }

        setContentFragment(R.id.main_layout_container, ProjectListFragment.newInstance())
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return ;
        }
        super.onBackPressed()
    }
}
