package com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.warrants

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.Warrant
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.activities.main.MainActivityVM


class WarrantsCompanyFragment : BaseFragment<MainActivityVM>() {
    override val viewModelClass: Class<MainActivityVM> = MainActivityVM::class.java
    override val layoutId: Int = R.layout.company_warrants
    override val observeLiveData: MainActivityVM.() -> Unit = {

    }
    private val array: ArrayList<String> = ArrayList()
    private lateinit var underlyinId: String
    private var isUpdate = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mainViewModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)
//        include10.visibility = View.GONE
//        cardViewProduct.visibility = View.VISIBLE
//        imgLeft.visibility = View.GONE
//        imgRight.visibility = View.GONE
//        txtTitle.text = ""
//
//        btnFilterWarrantsProduct.onClick {
//            if (mainViewModel.dataManger.isConfirmed()) {
//                val intent = FilterActivity.newIntent(context!!, underlyinId)
//                startActivity(intent)
//            } else {
//                startActivity(Intent(context, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
//        mainViewModel.getItemId().observe(this, Observer { it ->
//            if (!isUpdate) {
//                underlyinId = it!!
//                isUpdate = true
//            }
//
//            subscribeToItemId(underlyinId)
//            mainViewModel.getResponseWarrants().observe(this, Observer { result ->
//                mainViewModel.clearFilter()
//                if (result?.error != null) {
//                    parseError(result)
//                } else {
//                    if ((result?.data as? List<Warrant>)?.size == 0) {
//                        txtNoWarrants.visibility = View.VISIBLE
//                        recyclerWarrants1.visibility = View.INVISIBLE
//                    } else {
//                        recyclerWarrants1.visibility = View.VISIBLE
//                        txtNoWarrants.visibility = View.GONE
//                        subscribeToSocket(createArrayValors((result?.data as? List<Warrant>)!!))
//                        initRecyclerView(result.data)
//                    }
//                }
//            })
//        })

    }

//    private fun initRecyclerView(listWarrants: List<Warrant>) {
//        recyclerWarrants1.adapter = WarrantsAdapter(listWarrants.toMutableList(), context!!, object : WarrantsAdapter.WarrantsOnClickListener {
//            override fun onClick(id: Long) {
//                mainViewModel.getItemId().value = id.toString()
//                replaceFragmentSafely(WarrantsDetailFragment(), "DetailWarrant", false, true, R.id.containerLayout)
//            }
//        })
//        recyclerWarrants1.adapter.notifyDataSetChanged()
//    }
//
//    private fun subscribeToSocket(array: List<String>) {
////        mainViewModel.subscribeToSocket(array, Action1 { data -> clientMessageReceived(data) })
//    }
//
//    private fun createArrayValors(listWarrants: List<Warrant>): List<String> {
//        listWarrants.forEach { item -> array.add(item.valor!!) }
//        return array
//    }
//
//    private fun subscribeToItemId(id: String?) {
//        val filter = mainViewModel.getFilter()
//        mainViewModel.getWarrants(null, id, filter.contractOption, filter.maturityStartDate, filter.maturityEndDate, filter.strikePriceMin, filter.strikePriceMax, filter.topOnly, filter.paginationStart, filter.paginationCount)
//    }
//
//    private fun clientMessageReceived(data: String?) {
//        activity?.runOnUiThread {
//            Timber.d("update warrants $data")
//            try {
//
//            } catch (e: JsonSyntaxException) {
//                uiUpdate(product = GsonBuilder().create().fromJson(data, SocketModel::class.java))
//            }
//
//        }
//    }
//
//    private fun uiUpdate(product: SocketModel?) {
//        if (recyclerWarrants1 != null) {
//            (recyclerWarrants1.adapter as? WarrantsAdapter)?.updateItem(Warrant(product!!))
//        }
//    }
}
