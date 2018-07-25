package com.samapps.skelet.ui.adapters


/**
 * Created by sergey on 10/9/17.
 */

//class MidCapAdapter(private var items: MutableList<JBMidCapModel>, val context: Context, val listener: OnMidCapClickListener) : RecyclerView.Adapter<MidCapAdapter.ViewHolder>() {
//
//    @Inject
//    lateinit var storage: IUserStorage
//
//    init {
//        AppApplication.component.inject(this)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
//            NormalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.market_card_square, parent, false))
//
//    override fun getItemCount(): Int = items.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(items[position], context, listener)
//    }
//
//    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//
//        open fun bind(item: JBMidCapModel, context: Context, listener: OnMidCapClickListener) {
//
//        }
//    }
//
//    class NormalViewHolder(itemView: View?) : ViewHolder(itemView) {
//        val name = itemView!!.txtName
//        val txtProcentCard = itemView!!.txtProcentCard
//        val bid: TextView = itemView!!.txtBid
//        var background: CardView = itemView!!.cardView2
//
//        @SuppressLint("SetTextI18n")
//        override fun bind(item: JBMidCapModel, context: Context, listener: OnMidCapClickListener) {
//            name.text = item.title
//            txtProcentCard.text = item.priceChangePct?.times(100)?.format(2, "%%")
//            bid.text = item.lastTraded?.format(2)
//            calculateAlpha(adapterPosition, item.priceChangePct?.times(100))
//            background.onClick {
//                listener.onClick(item)
//            }
//        }
//
//        fun itemUI(context: Context, colorText: Int, colorBG: Int, bg: CardView, txtItems: List<TextView>) {
//            bg.setCardBackgroundColor(ContextCompat.getColor(context, colorBG))
//            txtItems.forEach { it.setTextColor(ContextCompat.getColor(context, colorText)) }
//        }
//
//        private fun calculateAlpha(adapterPosition: Int, priceChangePct: Double?) {
//
//            if (priceChangePct != null)
//                when {
//                    priceChangePct.times(100) > 0 -> {
//                        background.setCardBackgroundColor(Color.parseColor("#008675"))
//                        background.alpha = 1f - adapterPosition * 0.014f
//                        bid.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
//                        name.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
//                        txtProcentCard.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
//                    }
//                    priceChangePct.times(100) < 0 -> {
//                        background.setCardBackgroundColor(Color.parseColor("#971B30"))
//                        background.alpha = 0.2f + adapterPosition * 0.014f
//                        bid.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
//                        name.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
//                        txtProcentCard.textColor = ContextCompat.getColor(bid.context, R.color.colorPrimary)
//                    }
//                    else -> {
//                        background.alpha = 1f
//                        background.setCardBackgroundColor(Color.parseColor("#efeee5"))
//                        bid.textColor = ContextCompat.getColor(bid.context, R.color.black)
//                        name.textColor = ContextCompat.getColor(bid.context, R.color.black)
//                        txtProcentCard.textColor = ContextCompat.getColor(bid.context, R.color.black)
//                    }
//                }
//
//        }
//
//    }
//
//
//    fun updateItem(model: JBMidCapModel) {
//        val idx = items.indexOf(model)
//        Timber.d("Update midcap model $idx, item $model")
//        if (idx > -1) {
//            items[idx].lastTraded = model.lastTraded
//            items[idx].priceChangePct = model.priceChangePct
//            items = if (storage.getAlphabetic()) {
//                items.sortedBy { it.title }.toMutableList()
//            } else {
//                items.sortedByDescending { it.priceChangePct }.toMutableList()
//            }
//            notifyItemChanged(idx)
//        }
//    }
//
//    interface OnMidCapClickListener {
//        fun onClick(item: JBMidCapModel)
//    }
//}