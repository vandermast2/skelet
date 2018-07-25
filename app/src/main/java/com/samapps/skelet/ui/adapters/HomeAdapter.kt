package com.samapps.skelet.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.samapps.skelet.AppApplication
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.JBSMIModel
import com.samapps.skelet.dataFlow.storage.IUserStorage
import com.samapps.skelet.utils.extentions.format
import com.samapps.skelet.utils.extentions.onClick
import kotlinx.android.synthetic.main.market_card_square.view.*
import org.jetbrains.anko.textColor
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by sergey on 10/9/17.
 */

class HomeAdapter(private var items: MutableList<JBSMIModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    @Inject
    lateinit var storage: IUserStorage

    init {
        AppApplication.component.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.market_card_square, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.txtName
        val txtProcentCard = itemView.txtProcentCard
        val bid: TextView = itemView.txtBid
        var background: CardView = itemView.cardView2 as CardView


        fun bind(item: JBSMIModel, listener: OnItemClickListener) {
            name.text = item.title
            txtProcentCard.text = item.priceChangePct?.times(100)?.format(2) + "%"
            bid.text = item.lastTraded?.format(2)

            calculateAlpha(adapterPosition, item.priceChangePct?.times(100))

            background.onClick {
                listener.onClick(item)
            }

        }

        private fun calculateAlpha(adapterPosition: Int, priceChangePct: Double?) {

            if (priceChangePct != null) {
                val formattedPrice = priceChangePct.format(2).toDouble()
                when {
                    formattedPrice > 0 -> {
                        background.setCardBackgroundColor(Color.parseColor("#008675"))
                        background.alpha = 1f - adapterPosition * 0.04f
                        bid.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
                        name.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
                        txtProcentCard.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
                    }
                    formattedPrice < 0 -> {
                        background.setCardBackgroundColor(Color.parseColor("#971B30"))
                        background.alpha = 0.24f + adapterPosition * 0.04f
                        bid.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
                        name.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
                        txtProcentCard.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
                    }
                    else -> {
                        background.setCardBackgroundColor(Color.parseColor("#efeee5"))
                        bid.textColor = ContextCompat.getColor(bid.context, R.color.black)
                        name.textColor = ContextCompat.getColor(bid.context, R.color.black)
                        txtProcentCard.textColor = ContextCompat.getColor(bid.context, R.color.black)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(smiModel: JBSMIModel)
    }

    fun updateItem(model: JBSMIModel) {
        val oldIndex = items.indexOf(model)

        Timber.d("Update smi model $oldIndex, item $model")
        if (oldIndex > -1) {
            items[oldIndex].lastTraded = model.lastTraded
            items[oldIndex].priceChangePct = model.priceChangePct
            items = if (storage.getAlphabetic()) {
                items.sortedBy { it.title }.toMutableList()
            } else {
                items.sortedByDescending { it.priceChangePct }.toMutableList()
            }
            val newIndex = items.indexOf(model)
            if (oldIndex != newIndex) {
                notifyItemMoved(oldIndex, newIndex)
                notifyItemChanged(newIndex)
            } else {
                notifyItemChanged(oldIndex)
            }
        }
    }

}