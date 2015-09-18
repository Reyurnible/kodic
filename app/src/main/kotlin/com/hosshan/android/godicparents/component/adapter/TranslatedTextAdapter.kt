package com.hosshan.android.godicparents.component.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.model.TranslatedText
import java.util.ArrayList

/**
 * Created by shunhosaka on 15/09/16.
 */
public class TranslatedTextAdapter(activity: Activity, objects: ArrayList<TranslatedText> = ArrayList<TranslatedText>()) : ArrayRecyclerAdapter<TranslatedText, TranslatedTextAdapter.ViewHolder>(activity, objects) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_translated_text, parent, false)
        val viewHolder: TranslatedTextAdapter.ViewHolder = TranslatedTextAdapter.ViewHolder(view)
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item: TranslatedText? = getItem(position)
        item ?: return
        holder?.originalText?.text = item.text
        holder?.translatedText?.text = item.translatedText
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val originalText: TextView by bindView(R.id.translated_item_text)
        val translatedText: TextView by bindView(R.id.translated_item_translated_text)
    }

}