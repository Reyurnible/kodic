package com.hosshan.android.godecparents.component.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.hosshan.android.godecparents.model.Project
import com.hosshan.android.godicparents.R

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectAdapter(objects: List<Project>) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    val objects: List<Project>

    init {
        this.objects = objects;
    }

    override fun getItemCount(): Int {
        return objects.size()
    }

    override fun onBindViewHolder(holder: ProjectViewHolder?, position: Int) {
        val item: Project = objects.get(position)
        holder?.name?.setText(item.name)
        holder?.description?.setText(item.description)
        holder?.owner?.setText(item.owner.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProjectViewHolder? {
        val view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false)
        val viewHolder: ProjectViewHolder = ProjectViewHolder(view)
        return viewHolder;
    }

    public class ProjectViewHolder(view: View): RecyclerView.ViewHolder(view) {
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