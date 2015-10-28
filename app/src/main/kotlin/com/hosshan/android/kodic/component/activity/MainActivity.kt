package com.hosshan.android.kodic.component.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.drawer.DrawerFragment
import com.hosshan.android.kodic.component.fragment.project.ProjectListFragment
import com.mxn.soul.flowingdrawer_core.FlowingView
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : BaseActivity() {

    val drawerLayout: LeftDrawerLayout by bindView(R.id.main_drawerlayout)
    val toolbar: Toolbar by bindView(R.id.main_toolbar)
    val flowingView: FlowingView by bindView(R.id.main_flowingview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting Toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_inverse))
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isShownMenu) drawerLayout.closeDrawer() else drawerLayout.openDrawer()
        }

        val menuFragment: DrawerFragment = DrawerFragment.newInstance()
        // Drawerのメニューをセットする
        setContentFragment(R.id.main_layout_container_menu, menuFragment)

        drawerLayout.setFluidView(flowingView)
        drawerLayout.setMenuFragment(menuFragment)

        // コンテンツをセットする
        setContentFragment(R.id.main_layout_container, ProjectListFragment.newInstance())
    }

    override fun onBackPressed() {
        if (drawerLayout.isShownMenu) {
            drawerLayout.closeDrawer()
            return ;
        }
        super.onBackPressed()
    }
}
