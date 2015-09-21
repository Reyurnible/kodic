package com.hosshan.android.godicparents.component.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by shunhosaka on 15/09/12.
 */
public abstract class ArrayRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(val activity: Activity, val items: ArrayList<T>) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = items.size()

    public fun getAttachActivity(): Activity = activity

    public fun getItem(index: Int): T? {
        if (index < items.size() && index >= 0) {
            return items.get(index)
        }
        return null
    }

    public fun getPosition(item: T): Int {
        return this.items.indexOf(item)
    }

    public fun insert(index: Int, item: T) {
        this.items.add(index, item)
        notifyDataSetChanged()
    }

    public fun insertAll(index: Int, items: Collection<T>?) {
        items?.let {
            this.items.addAll(index, items!!)
            notifyDataSetChanged()
        }
    }

    public fun add(item: T) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    public fun addAll(items: Collection<T>?) {
        items?.let {
            this.items.addAll(items!!)
            notifyDataSetChanged()
        }
    }

    public fun remove(index: Int) {
        this.items.remove(index)
        notifyDataSetChanged()
    }

    public fun remove(item: T) {
        this.items.remove(item)
        notifyDataSetChanged()
    }

    public fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    public fun sort(comparator: Comparator<T>) {
        Collections.sort(this.items, comparator)
        notifyDataSetChanged()
    }

}