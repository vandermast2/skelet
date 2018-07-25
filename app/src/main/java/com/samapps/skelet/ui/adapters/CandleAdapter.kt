package com.samapps.skelet.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.CandleStickModel
import com.samapps.skelet.ui.customView.CandleStickChart
import com.samapps.skelet.utils.extentions.format
import com.samapps.skelet.utils.extentions.onClick
import kotlinx.android.synthetic.main.market_card_candle.view.*


/**
 * Created by sergey on 10/9/17.
 */

class CandleAdapter(private val max: Int, private var items: MutableList<CandleStickModel>, private val listener: OnCandleClickListener) : RecyclerView.Adapter<CandleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.market_card_candle, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, max)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.txtTitle
        val txtProcentCard = itemView.txtProcent
        val bid: TextView = itemView.txtRatio

        var background: ConstraintLayout = itemView.containerCandle

        fun bind(item: CandleStickModel, listener: OnCandleClickListener, max: Int) {
            val candleGraph: CandleStickChart = itemView.candleStickChart
            candleGraph.requestLayout()
            name.text = item.title

            val priceChange = item.priceChangePct.times(100).format(2).toDouble()
            txtProcentCard.text = "$priceChange%"

            when {
                priceChange < 0 -> txtProcentCard.setTextColor(ContextCompat.getColor(txtProcentCard.context, R.color.rouge))
                priceChange > 0 -> txtProcentCard.setTextColor(ContextCompat.getColor(txtProcentCard.context, R.color.blueGreen))
                else -> txtProcentCard.setTextColor(ContextCompat.getColor(txtProcentCard.context, R.color.grey_1))
            }
            bid.text = item.lastTraded.format(2)
            candleGraph.initialize(item, max)

            background.onClick {
                listener.onClick(item)
            }

        }

    }

    interface OnCandleClickListener {
        fun onClick(smiModel: CandleStickModel)
    }

    fun updateItem(model: CandleStickModel) {
//        val oldIndex = items.indexOf(model)
//
//        Timber.d("Update smi model $oldIndex, item $model")
//        if (oldIndex > -1) {
//            items[oldIndex].priceBid = model.priceBid
//            items[oldIndex].priceChangePct = model.priceChangePct
//            items = items.sortedByDescending { it.priceChangePct }.toMutableList()
//            val newIndex = items.indexOf(model)
//            if (oldIndex != newIndex) {
//                notifyItemMoved(oldIndex, newIndex)
//                notifyItemChanged(newIndex)
//            } else {
//                notifyItemChanged(oldIndex)
//            }
//        }
    }

}