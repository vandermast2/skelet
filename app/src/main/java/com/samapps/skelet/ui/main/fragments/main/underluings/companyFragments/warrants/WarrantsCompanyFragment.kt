package com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.warrants

import com.samapps.skelet.R
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.activities.main.MainActivityVM


class WarrantsCompanyFragment : BaseFragment<MainActivityVM>() {
    override fun getName(): String = ""

    override val viewModelClass: Class<MainActivityVM> = MainActivityVM::class.java
    override val layoutId: Int = R.layout.company_warrants
    override val observeLiveData: MainActivityVM.() -> Unit = {

    }
    private val array: ArrayList<String> = ArrayList()
    private lateinit var underlyinId: String
    private var isUpdate = false

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
