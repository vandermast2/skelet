package com.samapps.skelet.ui.base.adapters

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import java.util.*

/**
 * Base implementation of PagerAdapter for working and reusing views
 * @param T type of model to bind
 */
abstract class AbstractViewPagerAdapter<T>(private val data: MutableList<T> = mutableListOf())
    : PagerAdapter(), MutableList<T> by data {

    private val viewDeque: Deque<View> = ArrayDeque<View>()

    /**
     * SparseArray with currently existed views
     */
    private val existedViews = SparseArray<View>()

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = data.size

    override fun notifyDataSetChanged() {
        viewDeque.clear()
        existedViews.clear()
        super.notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View = (viewDeque.pollFirst()
            ?: LayoutInflater
                    .from(container.context)
                    .inflate(layoutId, container, false))
            .apply view@ {
                bind(this, this@AbstractViewPagerAdapter[position], position)
            }.also { container.addView(it) }
            .also { existedViews.put(position, it) }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        unBind(view, position)
        container.removeView(view)
        existedViews.remove(position)
        viewDeque.offerLast(view)
    }

    /**
     * id of item layout
     */
    abstract val layoutId: Int

    /**
     * All logic for views binding must be implemented here
     */
    abstract fun bind(view: View, item: T, position: Int)

    /**
     * All logic for clearing view must be implemented here
     */
    abstract fun unBind(view: View, position: Int)

}