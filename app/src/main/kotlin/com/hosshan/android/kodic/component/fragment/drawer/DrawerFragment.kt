package com.hosshan.android.kodic.component.fragment.drawer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.activity.DetailsActivity
import com.hosshan.android.kodic.component.adapter.DrawerMenuAdapter
import com.hosshan.android.kodic.component.fragment.license.LicenseFragment
import com.hosshan.android.kodic.component.fragment.login.LoginFragment
import com.hosshan.android.kodic.data.local.DrawerMenu
import com.mxn.soul.flowingdrawer_core.MenuFragment
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 2015/10/29.
 */
public class DrawerFragment : MenuFragment() {

    val listView: ListView by bindView(R.id.drawer_listview_menu)
    var adapter: DrawerMenuAdapter by Delegates.notNull()

    companion object {
        @JvmStatic public fun newInstance(): DrawerFragment {
            val fragment: DrawerFragment = DrawerFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DrawerComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DrawerMenuAdapter(activity, DrawerMenu.values)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            setupReveal(inflater?.inflate(R.layout.fragment_drawer, container, false))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, position, l ->
            val item: DrawerMenu = adapter.getItem(position)
            Timber.i("menu item click")
            item.let {
                // Itemを選択した時の処理
                when (item) {
                    DrawerMenu.Login -> DetailsActivity.createIntent(
                            activity,
                            "",
                            LoginFragment::class.java,
                            LoginFragment.createArgument())
                    DrawerMenu.Licence -> DetailsActivity.createIntent(
                            activity,
                            getString(item.title),
                            LicenseFragment::class.java,
                            LicenseFragment.createArgument())
                    else -> null
                }?.let {
                    // コンテンツがあれば表示する
                    startActivity(it)
                }
            }
        }
    }
}