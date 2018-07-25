package com.samapps.skelet.ui.adapters

/**
 * Created by sergey on 10/9/17.
 */

//class MarketsAdapter(val list: MutableList<Index>, val listener: OnItemClick) : RecyclerView.Adapter<MarketsAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context)
//            .inflate(R.layout.markets_card, parent, false))
//
//    override fun getItemCount(): Int = list.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position], listener)
//
//    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//        private val warrantsCard: ConstraintLayout = itemView!!.warrantsCard
//        private val txtAsk: TextView = itemView!!.txtAsk1
//        private val txtPercents: TextView = itemView!!.txtPercent1
//        private val txtName: TextView = itemView!!.txtNameMarket
//        fun bind(index: Index, listener: OnItemClick) {
//            if (index.hasDetails!!) {
//                warrantsCard.onClick { listener.onClick(index) }
//            }
//
//            val priceChangePct = index.priceChangePct?.times(100)?.format(2)!!.toDouble()
//            txtPercents.text = "$priceChangePct%"
//
//            when {
//                priceChangePct < 0 -> txtPercents.setTextColor(ContextCompat.getColor(itemView.context, R.color.rouge))
//                priceChangePct > 0 -> txtPercents.setTextColor(ContextCompat.getColor(itemView.context, R.color.blueGreen))
//                else -> txtPercents.setTextColor(ContextCompat.getColor(itemView.context, R.color.greyishBrownText))
//            }
//
//            txtName.text = index.marketsTitle
//            txtAsk.text = index.lastTraded?.format(2)
//        }
//    }
//
//    fun updateItem(index: Index) {
//        val idx = list.indexOf(index)
//        Timber.d("Update index $idx, item $index")
//        if (idx > -1) {
//            list[idx].lastTraded = index.lastTraded
//            list[idx].priceChangePct = index.priceChangePct
//            notifyItemChanged(idx)
//        }
//    }
//
//    interface OnItemClick {
//        fun onClick(index: Index)
//    }
//}