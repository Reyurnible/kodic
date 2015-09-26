package com.hosshan.android.kodic.component.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.ProjectListFragment
import timber.log.Timber
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
        toolbar.setTitleTextColor(getColor(R.color.primary_text_inverse))
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START) else drawerLayout.openDrawer(GravityCompat.START)
        }

        drawerToggle = ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close)
        //TODO Kotlinの拡張関数を利用してListenerの処理を上書きする

        // Setting NavigationDrawer
        drawerLayout.setDrawerListener(drawerToggle)

        navigationView.setNavigationItemSelectedListener (
                object : NavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem?): Boolean {

                        return false
                    }
                }
        )

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
