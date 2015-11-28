package com.hosshan.android.kodic.component.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.activity.DetailsActivity
import com.hosshan.android.kodic.component.fragment.translate.TranslateFragment
import com.hosshan.android.kodic.model.Project
import com.hosshan.android.kodic.util.ColorUtil
import jp.wasabeef.glide.transformations.BlurTransformation
import java.util.*
import kotlin.text.Regex

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectAdapter(activity: Activity, objects: ArrayList<Project> = ArrayList<Project>()) : ArrayRecyclerAdapter<Project, ProjectAdapter.ViewHolder>(activity, objects) {

    private val MATERIAL_COLOR_SIZE: Int = 19

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_project, parent, false)
        val viewHolder: ViewHolder = ViewHolder(view)
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val activity: Activity? = getAttachActivity()
        val item: Project? = getItem(position)
        item ?: return

        holder?.icon?.text = createIconName(item.name)
        holder?.icon?.background = createDrawable(activity, item.name)
        Glide.with(activity)
                .load(R.mipmap.img_triangles)
                .bitmapTransform(BlurTransformation(activity, 8))
                .into(holder?.backImage);
        holder?.name?.text = item.name
        holder?.description?.text = item.description
        holder?.description?.visibility = if (TextUtils.isEmpty(item.description)) View.GONE else View.VISIBLE
        holder?.owner?.text = item.owner.name
        holder?.itemView?.setOnClickListener {
            activity?.let {
                val intent: Intent = DetailsActivity.createIntent(activity!!, item.name, TranslateFragment::class.java, TranslateFragment.createArg(item.id))
                activity?.startActivity(intent)
            }
        }
    }

    fun createIconName(text: String): String {
        if (Regex("[A-Z]+").findAll(text, 0).count() > 0) {
            return Regex("[0-9a-z]+").replace(text, "")
        } else {
            return text.substring(0).toUpperCase()
        }
    }

    fun createDrawable(context: Context?, text: String): Drawable? {
        return createDrawable(context, text.length() % MATERIAL_COLOR_SIZE)
    }

    fun createDrawable(context: Context?, index: Int): Drawable? {
        context ?: return null
        val root: LayerDrawable = context.getDrawable(R.drawable.img_project_icon) as LayerDrawable
        val background: GradientDrawable = root.findDrawableByLayerId(R.id.background) as GradientDrawable
        background.setColor(ColorUtil.getMaterialDarkColor(context, index))
        val content: GradientDrawable = root.findDrawableByLayerId(R.id.content) as GradientDrawable
        content.setColor(ColorUtil.getMaterialColor(context, index))
        return root
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: TextView by bindView(R.id.project_item_text_icon)
        val backImage: ImageView by bindView(R.id.project_item_image_back)
        val name: TextView by bindView(R.id.project_item_text_name)
        val description: TextView by bindView(R.id.project_item_text_description)
        val owner: TextView by bindView(R.id.project_item_text_owner)
    }

}
