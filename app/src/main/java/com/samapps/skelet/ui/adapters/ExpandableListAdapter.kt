package com.samapps.skelet.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.Products
import com.samapps.skelet.utils.extentions.format
import com.samapps.skelet.utils.extentions.onClick
import kotlinx.android.synthetic.main.child_view.view.*
import kotlinx.android.synthetic.main.group_view.view.*


/**
 * Created by sergey on 10/31/17.
 */

class ExpandableListAdapter(private val context: Context, private val groups: ArrayList<List<Products?>>, val listener: OnExpandableClickListener) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = groups.size

    override fun getChildrenCount(groupPosition: Int): Int = groups[groupPosition].size

    override fun getGroup(groupPosition: Int): Any = groups[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Products? = groups[groupPosition][childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?,
                              parent: ViewGroup): View {

        var convertView = convertView

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.group_view, null)
        }

        val textGroup = convertView!!.findViewById<TextView>(R.id.textGroup)
        val imgUnderlying = convertView.imgUnderlying
        val ask = convertView.ask
        val bid = convertView.bid
        val interval = convertView.interval

        if (isExpanded) {
            imgUnderlying.visibility = View.VISIBLE
            ask.visibility = View.VISIBLE
            bid.visibility = View.VISIBLE
            interval.visibility = View.VISIBLE

        } else {
            imgUnderlying.visibility = View.INVISIBLE
            ask.visibility = View.INVISIBLE
            bid.visibility = View.INVISIBLE
            interval.visibility = View.INVISIBLE
        }


        when (groupPosition) {
            0 -> textGroup.text = "UNDERLYING"
            1 -> textGroup.text = "WARRANTS"
            else -> textGroup.text = "Group" + Integer.toString(groupPosition)
        }

        return convertView

    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.child_view, null)
        }
        val isNotification = convertView!!.isNotification
        val child = convertView.child
        val textChild = convertView.txtNameChild
        val priceChange = convertView.txtPriceChange
        val ask = convertView.txtAsk
        val bid = convertView.txtBid
        isNotification.setColorFilter(ContextCompat.getColor(context, R.color.rouge))
        if (groups[groupPosition][childPosition]?.isNotification!!) {
            isNotification.visibility = View.VISIBLE
        } else {
            isNotification.visibility = View.GONE
        }

        priceChange.text = (groups[groupPosition][childPosition]?.priceChangePct?.times(100))?.format(2)

        val priceChangeValue = groups[groupPosition][childPosition]?.priceChangePct?.times(100)!!
        when {
            priceChangeValue < 0.0 -> priceChange.setTextColor(ContextCompat.getColor(priceChange.context, R.color.rouge))
            priceChangeValue > 0.0 -> priceChange.setTextColor(ContextCompat.getColor(priceChange.context, R.color.blueGreen))
            else -> priceChange.setTextColor(ContextCompat.getColor(priceChange.context, R.color.greyishBrown))
        }
        ask.text = groups[groupPosition][childPosition]?.priceAsk?.format(2)
        bid.text = groups[groupPosition][childPosition]?.priceBid?.format(2)
        val btnDelete = convertView.btnDelete
        textChild.text = groups[groupPosition][childPosition]?.title



        child.onClick {
            if (groupPosition == 0) {
                listener.onClick(groups[groupPosition][childPosition]!!, "underlying")
            } else {
                listener.onClick(groups[groupPosition][childPosition]!!, "warrant")
            }
        }
        btnDelete.onClick {
            listener.onClick(groups[groupPosition][childPosition]!!, "delete")
        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    interface OnExpandableClickListener {
        fun onClick(model: Products, status: String)
    }
}