package com.hosshan.android.godicparents.component.adapter

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
public class TranslatedTextAdapter(objects: ArrayList<TranslatedText> = ArrayList<TranslatedText>()) : ArrayRecyclerAdapter<TranslatedText, TranslatedTextAdapter.ViewHolder>(objects) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view: View = LayoutInflater.from(parent?.getContext()).inflate(R.layout.item_translated_text, parent, false)
        val viewHolder: TranslatedTextAdapter.ViewHolder = TranslatedTextAdapter.ViewHolder(view)
        return viewHolder;
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item: TranslatedText? = getItem(position)
        item ?: return
        holder?.originalText?.setText(item.text)
        holder?.translatedText?.setText(item.translatedText)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val originalText: TextView by bindView(R.id.translated_item_text)
        val translatedText: TextView by bindView(R.id.translated_item_translated_text)
    }

}