package com.hosshan.android.godicparents.component.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.component.activity.BaseActivity
import com.hosshan.android.godicparents.component.fragment.TranslateFragment
import com.hosshan.android.godicparents.model.Project
import java.util.ArrayList

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectAdapter(activity: Activity, objects: ArrayList<Project> = ArrayList<Project>()) : ArrayRecyclerAdapter<Project, ProjectAdapter.ViewHolder>(activity, objects) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_project, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item: Project? = getItem(position)
        item ?: return
        holder?.name?.text = item.name
        holder?.description?.text = item.description
        holder?.owner?.text = item.owner.name
        holder?.itemView?.setOnClickListener {
            val activity: Activity? = getAttachActivity()
            if (activity is BaseActivity) {
                activity.addContentFragment(R.id.main_layout_container, TranslateFragment.newInstance(item.id))
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView by bindView(R.id.project_item_name)
        val description: TextView by bindView(R.id.project_item_description)
        val owner: TextView by bindView(R.id.project_item_owner)
    }

}