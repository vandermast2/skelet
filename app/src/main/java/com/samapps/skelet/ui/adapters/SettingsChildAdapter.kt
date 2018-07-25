package com.samapps.skelet.ui.adapters

/**
 * Created by sergey on 10/9/17.
 */

//class SettingsChildAdapter(private val newsModels: List<AlertsModel>, private val userInfoModel: UserInfoModel, val mainViewModel: MainActivityVM, private val listener: OnSettingsClickListener, private val telNumber: String) : RecyclerView.Adapter<SettingsChildAdapter.ViewHolder>() {
//    private val type: Int = 1
//    private val footer: Int = 2
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return when (viewType) {
//            type -> HeaderHolder(LayoutInflater.from(parent.context)
//                    .inflate(R.layout.settings_header, parent, false))
//            footer -> FooterHolder(LayoutInflater.from(parent.context)
//                    .inflate(R.layout.settings_footer, parent, false))
//            else -> NormalHolder(LayoutInflater.from(parent.context)
//                    .inflate(R.layout.setting_card, parent, false))
//        }
//    }
//
//    override fun getItemCount(): Int = newsModels.size + 2
//
//    override fun getItemViewType(position: Int): Int {
//        if (position == 0) {
//            return type
//        } else if (position == newsModels.size + 1) {
//            return footer
//        }
//        return super.getItemViewType(position)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        try {
//            if (holder is NormalHolder) {
//                holder.bind(newsModels[position - 1], listener)
//            } else if (holder is HeaderHolder) {
//                holder.bind(userInfoModel, listener, telNumber, mainViewModel)
//            } else if (holder is FooterHolder) {
//                holder.bind(listener)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
//
//    class HeaderHolder(itemView: View?) : ViewHolder(itemView) {
//        private var etLogin = itemView!!.etextLogin
//        private var btnLogin = itemView!!.btnSave
//        private var switchNotification = itemView!!.switchAlert
//        private var switchBoxes = itemView!!.switchBoxes
//        private var switchCandles = itemView!!.switchCandle
//        private var switchAlphabetical = itemView!!.switchAlphabetic
//        private var switchTop = itemView!!.switchTop
//        private var containerTop = itemView!!.containerTopPerfomance
//        private var containerAlphabetical = itemView!!.containerAlphabetic
//        private var containerBoxes = itemView!!.containerSwitchBox
//        private var containerCandle = itemView!!.containerCandleStick
//
//        private var switchNews = itemView!!.switchNews
//        fun bind(userInfoModel: UserInfoModel, listener: OnSettingsClickListener, telNumber: String, mainViewModel: MainActivityVM) {
//            if (userInfoModel.isNotificationForAlertsEnable!!) {
//                switchNotification.isChecked = true
//            }
//            if (mainViewModel.getBoxes()) {
//                switchCandles.isChecked = false
//                switchBoxes.isChecked = true
//            } else {
//                switchCandles.isChecked = true
//                switchBoxes.isChecked = false
//            }
//            if (mainViewModel.getTop()) {
//                switchAlphabetical.isChecked = false
//                switchTop.isChecked = true
//            } else {
//                switchAlphabetical.isChecked = true
//                switchTop.isChecked = false
//            }
//            containerTop.onClick { switchTop.isChecked = true }
//            containerAlphabetical.onClick { switchAlphabetical.isChecked = true }
//            containerBoxes.onClick { switchBoxes.isChecked = true }
//            containerCandle.onClick { switchCandles.isChecked = true }
//
//            if (telNumber != "00000000") {
//                etLogin.setText(telNumber, TextView.BufferType.EDITABLE)
//            }
//            btnLogin.onClick { listener.onClick(etLogin.text.toString(), "header") }
//            switchNotification.setOnCheckedChangeListener { _, isChecked ->
//                listener.onClick(isChecked.toString(), "switchNotification")
//            }
//            switchAlphabetical.setOnCheckedChangeListener { _, isChecked ->
//                switchTop.isChecked = !isChecked
//                listener.onClick(isChecked.toString(), "switchAlphabetical")
//            }
//            switchTop.setOnCheckedChangeListener { _, isChecked ->
//                switchAlphabetical.isChecked = !isChecked
//                listener.onClick(isChecked.toString(), "switchTop")
//            }
//            switchBoxes.setOnCheckedChangeListener { _, isChecked ->
//                switchCandles.isChecked = !isChecked
//                listener.onClick(isChecked.toString(), "switchBoxes")
//            }
//            switchCandles.setOnCheckedChangeListener { _, isChecked ->
//                switchBoxes.isChecked = !isChecked
//                listener.onClick(isChecked.toString(), "switchCandles")
//            }
//
//        }
//    }
//
//    class FooterHolder(itemView: View?) : ViewHolder(itemView) {
//        private val logout: ImageView = itemView!!.logout
//        fun bind(listener: OnSettingsClickListener) {
//            logout.onClick { listener.onClick("", "footer") }
//        }
//    }
//
//    class NormalHolder(itemView: View?) : ViewHolder(itemView) {
//        private val trash: ImageView = itemView!!.imgTrash
//        private var imgSettings = itemView!!.imgSettings
//        private val titile: TextView = itemView!!.txtTitle
//        fun bind(newsModel: AlertsModel, listener: OnSettingsClickListener) {
//            titile.text = newsModel.productName
//            trash.onClick { titile.context.startActivity(Intent(titile.context, AlertDelActivity::class.java).putExtra("productId", newsModel.productId)) }
//            trash.onClick { listener.onClick(newsModel.productId!!, "trash") }
//            if (newsModel.isUnderlying!!) {
//                imgSettings.onClick { titile.context.startActivity(Intent(titile.context, AlertUnderlyingActivity::class.java).putExtra("productId", newsModel.productId?.toInt())) }
//            } else {
//                imgSettings.onClick { titile.context.startActivity(Intent(titile.context, AlertWarrantsActivity::class.java).putExtra("productId", newsModel.productId?.toInt())) }
//            }
//
//        }
//
//    }
//
//    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//
//        open fun bind(newsModel: AlertsModel) {
//
//        }
//    }
//
//    interface OnSettingsClickListener {
//        fun onClick(number: String, type: String)
//    }
//}