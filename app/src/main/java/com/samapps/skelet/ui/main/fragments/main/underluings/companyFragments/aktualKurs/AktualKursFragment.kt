package com.juliusbaer.premarket.ui.fragments.companyFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samapps.skelet.R
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.activities.main.MainActivityVM
import timber.log.Timber


class AktualKursFragment : BaseFragment<MainActivityVM>() {
    override val viewModelClass: Class<MainActivityVM> = MainActivityVM::class.java
    override val layoutId: Int = R.layout.fragment_aktual_kurs
    override val observeLiveData: MainActivityVM.() -> Unit = {
        val model=this
//        getItemId().observe(this@AktualKursFragment, Observer { id ->
//            if (!isUpdated) {
//                itemId = id
//                isUpdated = true
//            }
//
//            updateGraph(itemId!!, Constants.INTRADAY)
//
//            model.getItem(itemId!!)
//            model.getUnderluingResult().observe(this, Observer { item ->
//                if (item?.error != null) {
//
//                } else {
//                    updateUIFromApi(item?.data as UnderlyingById)
//                    priceCurrency = item.data.priceCurrency
//                    model.subscribeToSocketContent(arrayListOf(item.data.valor!!))
//                    model.subscribeToSocket()
//                            .filter({ item -> item != null })
//                            .subscribe(
//                                    { item ->
//                                        clientMessageReceived(item)
//                                        Timber.d("Socket warrant detail connect $item")
//                                    }
//                                    , { e -> Timber.e("Socket warrant error $e") })
//                }
//
//            })
//        })
    }

    private var itemId: String? = null
    private var valor: String? = null
    private var priceCurrency: String? = null
    private var isUpdated = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        viewModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)
//
//        viewModel.
//        intradayLay?.onClick {
//            updateBtn(txtIntraday, imgIntraday, Constants.INTRADAY)
//        }
//        threeMonthLay?.onClick {
//            updateBtn(txtThreeMonth, imgThreeMonth, Constants.THREE_MONTH)
//        }
//        sixMonthLay?.onClick {
//            updateBtn(txtSixMonth, imgSixMonth, Constants.SIX_MONTH)
//        }
//        yearLay?.onClick {
//            updateBtn(txtOneYear, imgYear, Constants.YEAR)
//        }
//        txtOpenTop?.onClick {
//            replaceFragmentSafely(TopCompanyFragment(), Constants.WARRANTS_FRAGMENT_TAG, false, true, R.id.fragment)
//        }
    }

//    private fun updateUIFromApi(item: UnderlyingById?) {
//        valor = item?.valor
//        txtBidValueMarket?.text = item?.priceBid!!.format(2, item.priceCurrency!!)
//        txtAskValueMarket?.text = item.priceAsk!!.format(2, item.priceCurrency!!)
//        txtVolumeValueInfo?.setNumber(item.priceBidVolume!!)
//        txtVolumeRightValue?.setNumber(item.priceAskVolume!!)
//
//        val lastTradedInfo = item.priceChangePct?.times(100)?.format(2)!!.toDouble()
//        txtLastTradedInfo?.text = "$lastTradedInfo%"
//
//        when {
//            lastTradedInfo < 0 -> txtLastTradedInfo?.setTextColor(ContextCompat.getColor(context!!, R.color.rouge))
//            lastTradedInfo > 0 -> txtLastTradedInfo?.setTextColor(ContextCompat.getColor(context!!, R.color.blueGreen))
//            else -> txtLastTradedInfo?.setTextColor(ContextCompat.getColor(context!!, R.color.black))
//        }
//
//        txtOpeningValue?.text = item.priceOpen?.format(2, item.priceCurrency)
//        txtOpeningValue?.visibility = View.VISIBLE
//        txtOpening?.visibility = View.VISIBLE
//        imgLine9?.visibility = View.VISIBLE
//        txtValorValue?.text = item.valor
//        if (item.topWarrantsCount!! < 1) {
//            txtOpenTop.visibility = View.GONE
//            txtTopWarrant.visibility = View.GONE
//            imgLast.visibility = View.GONE
//        } else {
////            txtOpenTop.text = "Open ${item.topWarrantsCount} items"
//            if (UiUtils.isCompatWithN) {
//                txtOpenTop.text = Html.fromHtml("<u>" + "View" + "</u>", Html.FROM_HTML_MODE_COMPACT)
//            } else {
//                txtOpenTop.text = Html.fromHtml("<u>" + "View" + "</u>")
//            }
//        }
//        txtPriceValue.text = item.lastTraded?.format(2, item.priceCurrency)
//        txtDateValues?.setDate(item.priceDateTime, DATE_FORMAT)
//        txtRatio.text = "${item.maxLastTraded?.format(2)}/${item.minLastTraded?.format(2)} ${item.priceCurrency}"
//        txtClosingValue.text = item.priceSettled?.format(2, item.priceCurrency)
//    }
//
//    fun updateGraph(id: String, period: String) {
//        chart.clear()
//        viewModel.getChartDataResult(id, period)
//        viewModel.getChartResult().observe(this, Observer { result ->
//            if (result?.error != null) {
//
//            } else {
//                if (!(result?.data as ChartData).data?.isEmpty()!!) {
//                    ChartFabric(chart, result.data, period, context!!).setDataChart()
//                }
//            }
//
//        })
//    }
//
//    private fun updateBtn(text: TextView, img: ImageView, period: String) {
//        txtIntraday?.setTextColor(ContextCompat.getColor(context!!, R.color.greyishBrown))
//        txtThreeMonth?.setTextColor(ContextCompat.getColor(context!!, R.color.greyishBrown))
//        txtSixMonth?.setTextColor(ContextCompat.getColor(context!!, R.color.greyishBrown))
//        txtOneYear?.setTextColor(ContextCompat.getColor(context!!, R.color.greyishBrown))
//        imgIntraday?.visibility = View.INVISIBLE
//        imgThreeMonth?.visibility = View.INVISIBLE
//        imgSixMonth?.visibility = View.INVISIBLE
//        imgYear?.visibility = View.INVISIBLE
//        text.setTextColor(ContextCompat.getColor(context!!, R.color.deepBlue))
//        img.visibility = View.VISIBLE
//        itemId?.let { updateGraph(it, period) }
//    }
//
//    private fun clientMessageReceived(data: String?) {
//        activity?.runOnUiThread {
//            Timber.d("update before $data")
//            try {
//                uiUpdate(product = GsonBuilder().create().fromJson(data, SocketModel::class.java))
//            } catch (e: JsonSyntaxException) {
//
//            }
//
//        }
//    }
//
//    private fun uiUpdate(product: SocketModel?) {
//        if (product?.valor == valor) {
//            txtBidValueMarket?.text = (product?.priceBid!!.format(2, priceCurrency))
//            txtAskValueMarket?.text = (product.priceAsk!!.format(2, priceCurrency))
//            txtVolumeValueInfo?.setNumber(product.priceBidVolume!!)
//            txtVolumeRightValue?.setNumber(product.priceAskVolume!!)
//
//            val lastTradedInfo = product.priceChangePct?.format(2)!!.toDouble()
//            txtLastTradedInfo?.text = "$lastTradedInfo%"
//
//            when {
//                lastTradedInfo < 0 -> txtLastTradedInfo?.setTextColor(ContextCompat.getColor(context!!, R.color.rouge))
//                lastTradedInfo > 0 -> txtLastTradedInfo?.setTextColor(ContextCompat.getColor(context!!, R.color.blueGreen))
//                else -> txtLastTradedInfo?.setTextColor(ContextCompat.getColor(context!!, R.color.black))
//            }
//            txtDateValues?.setDate(product.priceDateTime, DATE_FORMAT)
//            txtRatio?.text = "${product.maxLastTraded?.format(2)}/${product.minLastTraded?.format(2)} $priceCurrency"
//            txtPriceValue?.text = product.lastTraded?.format(2, priceCurrency)
//        }
//
//    }

}