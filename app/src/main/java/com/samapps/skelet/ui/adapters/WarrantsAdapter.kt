package com.samapps.skelet.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.Warrant
import timber.log.Timber

/**
 * Created by sergey on 10/9/17.
 */

class WarrantsAdapter(private val items: MutableList<Warrant>, val context: Context, private val listener: WarrantsOnClickListener) : RecyclerView.Adapter<WarrantsAdapter.ViewHolder>() {
    companion object {
        private val FOOTER_VIEW = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            when (viewType) {
                FOOTER_VIEW -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.phone_container, parent, false))
                else -> NormalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.varrants_card, parent, false))
            }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            when (holder) {
                is NormalViewHolder -> holder.bind(items[position], context, listener)
//                is HeaderViewHolder -> holder.bind(items[position],context )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        items.size -> super.getItemViewType(position)
        else -> super.getItemViewType(position)
    }

    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        open fun bind(item: Warrant, context: Context) {

        }
    }

    class HeaderViewHolder(itemView: View?) : ViewHolder(itemView)

    class NormalViewHolder(itemView: View?) : ViewHolder(itemView) {
        private val txtName: TextView = itemView!!.txtNameWarrants
        private val txtPutCall: TextView = itemView!!.txtPutCall
        private val txtBid: TextView = itemView!!.txtBid
        private val txtAsk: TextView = itemView!!.txtAsk
        private val txtProcents: TextView = itemView!!.txtDate
        private val warrantCard: ConstraintLayout = itemView!!.warrantsCard
        private val imgStar: ImageView = itemView!!.imgStar
        private val txtExpiry: TextView = itemView!!.txtExpiry
        private val txtStrike: TextView = itemView!!.txtStrike

        fun bind(item: Warrant, context: Context, listener: WarrantsOnClickListener) {
            txtName.text = item.title
            txtPutCall.text = item.strikeType
            txtBid.text = item.priceBid?.format(2)
            txtAsk.text = item.priceAsk?.format(2)
            txtExpiry.setDate(item.exerciseDate!!, "dd-MM-yyyy")
            txtStrike.text = item.strikeLevel?.format(2)
            if (item.isTop!!) {
                imgStar.setImageDrawable(ContextCompat.getDrawable(imgStar.context, R.drawable.ic_star_warrant_fill))
            } else {
                imgStar.setImageDrawable(ContextCompat.getDrawable(imgStar.context, R.drawable.ic_star_warrant))
            }
            if (item.priceChangePct!! < 0) {
                txtProcents.setTextColor(ContextCompat.getColor(context, R.color.rouge))
                txtProcents.text = "${item.priceChangePct?.format(2)}%"
            } else {
                txtProcents.setTextColor(ContextCompat.getColor(context, R.color.blueGreen))
                txtProcents.text = "+${item.priceChangePct?.format(2)}%"
            }

            warrantCard.onClick {
                listener.onClick(item.id!!)
                Timber.d("WarrantsDetailFragment adapter id ${item.id}")
            }
        }
    }

    interface WarrantsOnClickListener {
        fun onClick(id: Long)
    }

    fun updateItem(index: Warrant) {
        val idx = items.indexOf(index)
        Timber.d("update warrant idx $idx, item $index")
        if (idx > -1) {
            items[idx].priceAsk = index.priceAsk
            items[idx].priceBid = index.priceBid
            items[idx].strikeType = index.strikeType
            items[idx].priceChangePct = index.priceChangePct
            notifyItemChanged(idx)
        }
    }
}