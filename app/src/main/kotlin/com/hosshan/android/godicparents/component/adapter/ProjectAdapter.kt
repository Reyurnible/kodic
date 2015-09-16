package com.hosshan.android.godicparents.component.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.model.Project
import java.util.ArrayList

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectAdapter(objects: ArrayList<Project> = ArrayList<Project>()) : ArrayRecyclerAdapter<Project, ProjectAdapter.ViewHolder>(objects) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view: View = LayoutInflater.from(parent?.getContext()).inflate(R.layout.item_project, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item: Project? = getItem(position)
        item?.let {
            holder?.name?.setText(item!!.name)
            holder?.description?.setText(item!!.description)
            holder?.owner?.setText(item!!.owner.name)
        }
    }

    public class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val description: TextView
        val owner: TextView

        init {
            name = view.findViewById(R.id.project_item_name) as TextView
            description = view.findViewById(R.id.project_item_description) as TextView
            owner = view.findViewById(R.id.project_item_owner) as TextView
        }
    }

}