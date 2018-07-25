package com.samapps.skelet.ui.main.fragments.main.underluings.smi

import android.os.Bundle
import android.provider.Settings.System.DATE_FORMAT
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.samapps.skelet.AppApplication
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.CandleStickModel
import com.samapps.skelet.dataFlow.models.apiModels.Index
import com.samapps.skelet.dataFlow.models.apiModels.JBSMIModel
import com.samapps.skelet.dataFlow.models.responseModel.Response
import com.samapps.skelet.dataFlow.models.socketModel.SocketModel
import com.samapps.skelet.ui.adapters.CandleAdapter
import com.samapps.skelet.ui.adapters.HomeAdapter
import com.samapps.skelet.ui.base.BaseUnderlyingFragment
import com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.company.CompanyFragment
import com.samapps.skelet.utils.Constants
import com.samapps.skelet.utils.extentions.onClick
import com.samapps.skelet.utils.extentions.replaceFragmentSafely
import kotlinx.android.synthetic.main.fragment_smi.*
import kotlinx.android.synthetic.main.header_smi.*
import org.jetbrains.anko.textColor
import timber.log.Timber


class SMIFragment : BaseUnderlyingFragment<SMIViewModel>() {
    override val viewModelClass: Class<SMIViewModel> = SMIViewModel::class.java
    override val layoutId: Int = R.layout.fragment_smi
    override val observeLiveData: SMIViewModel.() -> Unit = {}

    companion object {
        const val SMI_INDEX = "998089"
    }

    private var array: ArrayList<String> = arrayListOf(SMI_INDEX)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppApplication.component.inject(this)
        smiLay.onClick { }

//        viewModel.getSmiIndexRequest()
//        viewModel.getSMIUnderluing()
//        viewModel.getSMIUnderluingCandle()
//        setHeaderSMIFromApi()
//        subscribeToSocketHeaderSMI()

        onClickBoxes()
        imgPhone.onClick {
            callToTrader(viewModel.getPhoneNumber())
        }
    }




    private fun isBoxesRecyclerInitialize() {
        if (viewModel.getBoxes()) {
            initRecycler()
        } else {
            viewModel.getSMIResponseCandle().observe(this, Observer { it ->
                initCandleRecycler(it)
            })
        }
    }

    private fun setRecyclerAutomatically() {
        setBoxesIcon()
        isBoxesRecyclerInitialize()
    }

    private fun hidePercentsHeader() {
        txtLeftPercent.visibility = View.GONE
        txtCenterLeft.visibility = View.GONE
        txtCenter.visibility = View.GONE
        txtRightPercent.visibility = View.GONE
        txtCenterRight.visibility = View.GONE
    }

    private fun setPercentsHeader() {
        txtLeftPercent.visibility = View.VISIBLE
        txtCenterLeft.visibility = View.VISIBLE
        txtCenter.visibility = View.VISIBLE
        txtRightPercent.visibility = View.VISIBLE
        txtCenterRight.visibility = View.VISIBLE
    }

    private fun initCandleRecycler(it: Response<List<CandleStickModel>>?) {
        if (it?.error != null) {
            parseError(it)
        } else {
            setValuesOnHeader(getMaxPositiveValue((it?.data!!)))
            recyclerViewHome.layoutManager = LinearLayoutManager(context)
            recyclerViewHome.setHasFixedSize(true)
            recyclerViewHome.adapter = CandleAdapter(getMaxPositiveValue(it.data), getListCandles(it), object : CandleAdapter.OnCandleClickListener {
                override fun onClick(smiModel: CandleStickModel) {
                    viewModel.getItemId().value = smiModel.productId.toString()
                    replaceFragmentSafely(CompanyFragment(), Constants.COMPANY_FRAGMENT_TAG, false, true, R.id.containerLayout, R.style.FragStyle)
                }
            })
            spinnerSMI.visibility = View.GONE
            recyclerViewHome.adapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerView(it: Response?) {
        if (it?.error != null) {
            parseError(it)
        } else {
            with(recyclerViewHome){
                layoutManager = GridLayoutManager(context, 4)
                setHasFixedSize(true)
                adapter = HomeAdapter(getListBoxes(it!!), object : HomeAdapter.OnItemClickListener {
                    override fun onClick(smiModel: JBSMIModel) {
                        viewModel.getItemId().value = smiModel.id.toString()
                        replaceFragmentSafely(CompanyFragment(), Constants.COMPANY_FRAGMENT_TAG, false, true, R.id.containerLayout, R.style.FragStyle)
                    }
                })
                (adapter as HomeAdapter).notifyDataSetChanged()
            }

            spinnerSMI.visibility = View.GONE

        }

    }

    private fun getListBoxes(it: Response<List<JBSMIModel>>) = if (viewModel.getTop()) {
        (it.data as List<JBSMIModel>).sortedByDescending { it.priceChangePct }.toMutableList()
    } else {
        (it.data as List<JBSMIModel>).sortedBy { it.title }.toMutableList()
    }

    private fun getListCandles(it: Response<List<CandleStickModel>>) = if (viewModel.getTop()) {
        (it.data as List<CandleStickModel>).sortedByDescending { it.priceChangePct }.toMutableList()
    } else {
        (it.data as List<CandleStickModel>).sortedBy { it.title }.toMutableList()
    }


    private fun initRecycler() {
        viewModel.getSMIResponse().observe(this, Observer { it ->
            subscribeToSocketSMIList(it)
            setRecyclerView(it)
            viewModel.subscribeToSocketContent(createValorArray(it?.data as? List<JBSMIModel>))
            viewModel.subscribeToSocket()
                    .filter({ item -> item != null })
                    .subscribe(
                            { item ->
                                clientMessageReceivedList(item)
                                Timber.d("Socket connect $item")
                            }
                            , { e -> Timber.e("Socket error $e") })
        })
    }

    private fun createValorArray(data: List<JBSMIModel>?): ArrayList<String> {
        data?.forEach { it -> array.add(it.valor!!) }
        return array
    }

    private fun subscribeToSocketHeaderSMI() {
        viewModel.subscribeToSocket()
                .subscribe(
                        { item ->
                            clientMessageReceived(item)
                            Timber.d("Socket connect $item")
                        }
                        , { e -> Timber.e("Socket error $e") })
    }

    private fun setHeaderSMIFromApi() {
        viewModel.getSmiIndex().observe(this, Observer { it ->
            if (it?.error != null) {

            } else {
                val item = it?.data as List<Index>
                setIndex(item[0].title!!, item[0].lastTraded.toString(), item[0].priceChangePct!!, item[0].date!!)
                include11.onClick {
                    viewModel.getItemId().value = item[0].id.toString()
                    replaceFragmentSafely(IndexDetailFragment(), "Index_Detail", false, true, R.id.containerLayout)
                }
            }

        })
    }

    private fun subscribeToSocketSMIList(it: Response<List<JBSMIModel>>) {
        if (it.error == null) {
            val array: ArrayList<String> = ArrayList()
            (it.data)?.forEach { item -> array.add(item.valor.toString()) }
//            mainViewModel.subscribeToSocket(array, Action1 { data -> clientMessageReceivedList(data) })
        }


    }

    private fun clientMessageReceivedList(data: String?) {
        activity?.runOnUiThread {
            Timber.d("update smi data $data")
            try {
                uiUpdate(product = GsonBuilder().create().fromJson(data, SocketModel::class.java))
            } catch (e: Throwable) {
//                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun uiUpdate(product: SocketModel?) {
        (recyclerViewHome?.adapter as? HomeAdapter).updateItem(JBSMIModel(product!!))
    }

    private fun setIndex(name: String?, prise: String?, priceChangePct: Double?, date: Long?) {
        if (!name.isNullOrEmpty()) {
            txtHomeMareket?.text = name
        }
        if (priceChangePct != null) {
            if (priceChangePct < 0) {
//                txtLastTraderMidCap?.textColor = ContextCompat.getColor(context!!, R.color.rouge)
                txtPercentMidCap?.textColor = ContextCompat.getColor(context!!, R.color.rouge)
            } else {
//                txtLastTraderMidCap?.textColor = ContextCompat.getColor(context!!, R.color.blueGreen)
                txtPercentMidCap?.textColor = ContextCompat.getColor(context!!, R.color.blueGreen)
            }
        }
        txtLastTraderMidCap?.text = prise?.format(2)
        txtPercentMidCap?.text = priceChangePct?.times(100)?.format(2, "%%")
        if (spinnerIndexSMI != null) {
            spinnerIndexSMI?.visibility = View.GONE
        }
        txtDate?.setDate(date, DATE_FORMAT)
    }

    private fun clientMessageReceived(messageBody: String) {
        activity?.runOnUiThread {
            Timber.d("update smi index $messageBody")
            try {
                val product = GsonBuilder().create().fromJson(messageBody, SocketModel::class.java)
                if (product?.valor == "998089") {
                    setIndex(product.title, product.priceSettled, product.priceChangePct!!, product.priceDateTime!!)
                }
            } catch (e: JsonSyntaxException) {
//                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            } catch (t: NumberFormatException) {
//                Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        setRecyclerAutomatically()
        when {
            array.isNotEmpty() -> {
                Timber.d("Socket subscribe")
                viewModel.subscribeToSocketContent(array)
            }
        }

    }

    override fun onPause() {
        super.onPause()
        when {
            array.isNotEmpty() -> {
                viewModel.unsubscribeFromSocket(array)
                Timber.d("Socket unsubscribe")
            }
        }
    }
}
