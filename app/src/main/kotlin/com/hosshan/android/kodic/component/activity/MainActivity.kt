package com.hosshan.android.kodic.component.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.project.ProjectListFragment
import com.yalantis.guillotine.animation.GuillotineAnimation

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : BaseActivity() {

    companion object {
        private const val RIPPLE_DURATION: Long = 250;
    }

    val rootLayout: FrameLayout by bindView(R.id.main_layout_root)
    val toolbar: Toolbar by bindView(R.id.main_toolbar)
    val imageHamburger: ImageView by bindView(R.id.main_imageview_hamburger)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting Toolbar
        setSupportActionBar(toolbar)
        supportActionBar.title = null

        // Setting GuillotineMenu
        val guillotineMenu: View = LayoutInflater.from(this).inflate(R.layout.view_guillotine, null)
        rootLayout.addView(guillotineMenu)
        GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), imageHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build()

        // Setting Content Fragment
        setContentFragment(R.id.main_layout_container, ProjectListFragment.newInstance())
    }
}
