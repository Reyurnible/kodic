package com.hosshan.android.kodic.component.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.data.local.DrawerMenu

/**
 * Created by shunhosaka on 2015/10/29.
 */
class DrawerMenuAdapter(context: Context, contents: Array<DrawerMenu>) : ArrayAdapter<DrawerMenu>(context, R.layout.item_drawer_menu, contents) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        convertView?.let {
            bindView(it.tag as ViewHolder, position)
            return convertView
        }
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_drawer_menu, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        view.tag = viewHolder
        bindView(viewHolder, position)
        return view
    }

    private fun bindView(viewHolder: ViewHolder, position: Int) {
        val item: DrawerMenu = getItem(position)
        viewHolder.titleTextView.setText(item.title)
    }

    class ViewHolder {
        val titleTextView: TextView

        constructor(view: View) {
            titleTextView = view.findViewById(R.id.item_drawer_title) as TextView
        }
    }

}