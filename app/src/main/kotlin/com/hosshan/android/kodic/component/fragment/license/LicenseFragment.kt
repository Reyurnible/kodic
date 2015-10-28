package com.hosshan.android.kodic.component.fragment.license

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.adapter.LicenseAdapter
import com.hosshan.android.kodic.component.fragment.BaseFragment
import com.hosshan.android.kodic.data.local.License
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 2015/10/17.
 */
public class LicenseFragment : BaseFragment() {

    companion object {
        @JvmStatic public fun newInstance(): LicenseFragment {
            val fragment: LicenseFragment = LicenseFragment()
            fragment.arguments = createArgument()
            return fragment
        }

        @JvmStatic public fun createArgument(): Bundle =
                Bundle()
    }

    val listView: ListView by bindView(R.id.license_listview)
    var adapter: LicenseAdapter by Delegates.notNull()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        LicenseComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = LicenseAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_license, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, position, l ->
            val item: License = adapter.getItem(position)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)));
        }
    }
}