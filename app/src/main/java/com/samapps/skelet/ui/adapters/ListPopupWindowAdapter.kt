package com.samapps.skelet.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samapps.skelet.R
import kotlinx.android.synthetic.main.item.view.*


/**
 * Created by sergey on 10/30/17.
 */

class ListPopupWindowAdapter(context: Context, private var mItemMenuList: List<ItemMenu>) : android.widget.BaseAdapter() {

    private var layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return mItemMenuList.size
    }

    override fun getItem(i: Int): ItemMenu {
        return mItemMenuList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, null)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.tvTitle.text = getItem(position).title
        holder.ivImage.setImageResource(getItem(position).imageRes)

        return convertView
    }

    internal class ViewHolder(view: View) {
        var tvTitle = view.text
        var ivImage = view.image

    }
}