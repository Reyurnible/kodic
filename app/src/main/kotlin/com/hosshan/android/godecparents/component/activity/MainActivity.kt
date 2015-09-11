package com.hosshan.android.godicparents.component.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import butterknife.bindView
import com.hosshan.android.godecparents.component.activity.BaseActivity
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.component.fragment.TopFragment
import timber.log.Timber

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : BaseActivity() {

    val drawerLayout: DrawerLayout by bindView(R.id.main_drawerlayout)
    val navigationView: NavigationView by bindView(R.id.main_navigation_view)
    val toolbar: Toolbar by bindView(R.id.main_toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentFragment(R.id.main_layout_container, TopFragment.newInstance())

        // Setting Toolbar
        setToolbar()

        // Setting NavigationDrawer
        setNavigationDrawer()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(R.color.primary_text_inverse)
        toolbar.setBackgroundColor(R.color.primary)
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
    }

    private fun setNavigationDrawer() {
        drawerLayout.setDrawerListener(
                object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                        Timber.d("onDrawerSlide")
                    }

                    override fun onDrawerOpened(drawerView: View?) {
                        Timber.d("onDrawerOpened")
                    }

                    override fun onDrawerClosed(drawerView: View?) {
                        Timber.d("onDrawerClosed")
                    }

                    override fun onDrawerStateChanged(newState: Int) {
                        Timber.d("onDrawerStateChanged")
                    }
                })

        navigationView.setNavigationItemSelectedListener (
                object : NavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(p0: MenuItem?): Boolean {

                        return false
                    }
                }
        )

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START) else drawerLayout.openDrawer(GravityCompat.START)
        }

    }

}