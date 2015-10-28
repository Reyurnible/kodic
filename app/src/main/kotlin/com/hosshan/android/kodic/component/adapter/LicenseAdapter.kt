package com.hosshan.android.kodic.component.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.data.local.License

/**
 * Created by shunhosaka on 2015/10/29.
 */
class LicenseAdapter(context: Context) : ArrayAdapter<License>(context, R.layout.item_license, License.values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        convertView?.let {
            bindView(it.tag as ViewHolder, position)
            return convertView
        }
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_license, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        view.tag = viewHolder
        bindView(viewHolder, position)
        return view
    }

    private fun bindView(viewHolder: ViewHolder, position: Int) {
        val item: License = getItem(position)
        viewHolder.titleTextView.text = item.title
    }

    class ViewHolder {
        val titleTextView: TextView

        constructor(view: View) {
            titleTextView = view.findViewById(R.id.item_license_title) as TextView
        }
    }

}