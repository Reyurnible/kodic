package com.hosshan.android.kodic.component.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.hosshan.android.kodic.R

/**
 * Created by shunhosaka on 15/09/26.
 */
public class DetailsActivity : BaseActivity() {

    companion object {
        const val EXTRA_TITLE: String = "extra_title"
        const val EXTRA_FRAGMENT_CLASS: String = "extra_fragment_class"
        const val EXTRA_FRAGMENT_ARG: String = "extra_fragment_arg"

        @JvmStatic fun createIntent<T : Fragment>(context: Context, title: CharSequence?, fragmentClass: Class<T>, fragmentArg: Bundle?): Intent {
            val intent: Intent = Intent(context, DetailsActivity::class.java)
            title?.let {
                intent.putExtra(EXTRA_TITLE, it)
            }
            intent.putExtra(EXTRA_FRAGMENT_CLASS, fragmentClass)
            fragmentArg?.let {
                intent.putExtra(EXTRA_FRAGMENT_ARG, it)
            }
            return intent
        }
    }

    val toolbar: Toolbar by bindView(R.id.details_toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar.setDisplayHomeAsUpEnabled(true)
        val intent: Intent = intent
        if (intent.hasExtra(EXTRA_TITLE)) {
            title = intent.getCharSequenceExtra(EXTRA_TITLE)
        }
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_inverse))
        toolbar.navigationIcon = getDrawable(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val fragmentClass: Class<Fragment> = intent.getSerializableExtra(EXTRA_FRAGMENT_CLASS) as Class<Fragment>
        val fragment: Fragment? = getFragment(fragmentClass)
        if (intent.hasExtra(EXTRA_FRAGMENT_ARG)) {
            fragment?.arguments = intent.getParcelableExtra(EXTRA_FRAGMENT_ARG)
        }
        fragment?.let {
            setContentFragment(R.id.details_layout_container, it)
        }
    }

    private fun getFragment<T : Fragment>(fragmentClass: Class<T>?): T? = fragmentClass?.newInstance()

}
