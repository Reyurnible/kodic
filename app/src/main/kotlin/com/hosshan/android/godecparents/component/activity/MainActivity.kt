package com.hosshan.android.godecparents.component.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import butterknife.bindView

/**
 * Created by shunhosaka on 15/09/05.
 */
public class MainActivity : AppCompatActivity() {

    val toolbar : Toolbar by bindView(R.id.main_toolbar)
    val containerLayout : FrameLayout by bindView(R.id.main_layout_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
