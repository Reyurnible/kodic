package com.hosshan.android.godicparents.component.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import butterknife.bindView
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.component.fragment.TopFragment

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : BaseActivity() {

    val toolbar : Toolbar by bindView(R.id.main_toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentFragment(R.id.main_layout_container, TopFragment.createFragment())
    }
}