package com.hosshan.android.kodic.component.fragment.license

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import butterknife.bindView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.fragment.BaseFragment

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        LicenseComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_license, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO set list adapter
    }
}