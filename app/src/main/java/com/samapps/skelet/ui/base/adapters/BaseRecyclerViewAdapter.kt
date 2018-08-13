package com.samapps.skelet.ui.base.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samapps.skelet.R

/**
 * Base adapter for recycler view
 */
abstract class BaseRecyclerViewAdapter<TData, TViewHolder : RecyclerView.ViewHolder>
(context: Context, data: List<TData> = listOf())
    : RecyclerView.Adapter<TViewHolder>(), DataAdapter<TData> {

    protected val context: Context = context.applicationContext
    protected val inflater: LayoutInflater = LayoutInflater.from(ContextThemeWrapper(context, R.style.AppTheme))
    protected val data: MutableList<TData> = data.toMutableList()

    override fun getItemCount(): Int {
        return data.size
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItem(position: Int): TData {
        return data[position]
    }


    override fun add(`object`: TData): Boolean {
        return data.add(`object`)
    }

    override fun set(position: Int, `object`: TData): TData {
        return data.set(position, `object`)
    }

    override fun remove(`object`: TData): Boolean {
        return data.remove(`object`)
    }

    override fun remove(position: Int): TData {
        return data.removeAt(position)
    }


    override fun updateListItems(newObjects: List<TData>, callback: DiffUtil.Callback) {
        val diffResult = DiffUtil.calculateDiff(callback)
        data.clear()
        data.addAll(newObjects)
        diffResult.dispatchUpdatesTo(this)
    }

    override val all: List<TData>
        get() = data

    override fun clear() {
        data.clear()
    }

    override fun addAll(collection: Collection<TData>): Boolean {
        return data.addAll(collection)
    }

    override val snapshot: List<TData>
        get() = data.toList()

    override fun getItemPosition(`object`: TData): Int {
        return data.indexOf(`object`)
    }

    override fun insert(`object`: TData, position: Int) {
        data.add(position, `object`)
    }

    override fun insertAll(`object`: Collection<TData>, position: Int) {
        data.addAll(position, `object`)
    }

    override fun isEmpty() = data.isEmpty()

    override fun isNotEmpty() = data.isNotEmpty()
}