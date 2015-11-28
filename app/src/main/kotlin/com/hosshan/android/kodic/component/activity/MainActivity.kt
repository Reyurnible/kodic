package com.hosshan.android.kodic.component.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.license.LicenseFragment
import com.hosshan.android.kodic.component.fragment.login.LoginFragment
import com.hosshan.android.kodic.component.fragment.project.ProjectListFragment
import com.yalantis.guillotine.animation.GuillotineAnimation
import com.yalantis.guillotine.interfaces.GuillotineListener

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

    private var guillotineAnimation: GuillotineAnimation? = null
    private var isGuillotineMenuOpend: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting Toolbar
        setSupportActionBar(toolbar)
        supportActionBar.title = null

        // Setting GuillotineMenu
        val guillotineMenu: View = LayoutInflater.from(this).inflate(R.layout.view_guillotine, null)
        guillotineMenu.findViewById(R.id.guillotine_layout_login).setOnClickListener(guillotineMenuClickListener)
        guillotineMenu.findViewById(R.id.guillotine_layout_license).setOnClickListener(guillotineMenuClickListener)
        rootLayout.addView(guillotineMenu)
        guillotineAnimation = GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), imageHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .setGuillotineListener(object : GuillotineListener {
                    override fun onGuillotineClosed() {
                        isGuillotineMenuOpend = false;
                    }
                    override fun onGuillotineOpened() {
                        isGuillotineMenuOpend = true;
                    }
                })
                .build()

        // Setting Content Fragment
        setContentFragment(R.id.main_layout_container, ProjectListFragment.newInstance())
    }

    private val guillotineMenuClickListener = View.OnClickListener {
        when (it.id) {
            R.id.guillotine_layout_login ->
                DetailsActivity.createIntent(
                        this,
                        "",
                        LoginFragment::class.java,
                        LoginFragment.createArgument())
            R.id.guillotine_layout_license ->
                DetailsActivity.createIntent(
                        this,
                        getString(R.string.title_license),
                        LicenseFragment::class.java,
                        LicenseFragment.createArgument())
            else -> null
        }?.let {
            // コンテンツがあれば表示する
            startActivity(it)
        }
    }

    override fun onBackPressed() {
        if (isGuillotineMenuOpend) {
            guillotineAnimation?.close()
            return
        }
        super.onBackPressed()
    }
}
