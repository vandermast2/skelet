package com.samapps.skelet.ui.base.adapters

import androidx.recyclerview.widget.DiffUtil

interface DataAdapter<TData> {

    fun getItemCount(): Int

    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getItem(position: Int): TData

    fun add(`object`: TData): Boolean

    fun set(position: Int, `object`: TData): TData

    fun remove(`object`: TData): Boolean

    fun remove(position: Int): TData

    fun updateListItems(newObjects: List<TData>, callback: DiffUtil.Callback)

    val all: List<TData>

    fun clear()

    fun addAll(collection: Collection<TData>): Boolean

    val snapshot: List<TData>

    fun getItemPosition(`object`: TData): Int

    fun insert(`object`: TData, position: Int)

    fun insertAll(`object`: Collection<TData>, position: Int)

    fun isEmpty(): Boolean

    fun isNotEmpty(): Boolean
}