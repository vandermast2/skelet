package com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.top

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.Warrant
import com.samapps.skelet.dataFlow.models.socketModel.SocketModel
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.activities.main.MainActivityVM
import timber.log.Timber


class TopCompanyFragment : BaseFragment<MainActivityVM>() {
    override fun getName(): String = ""

    override val viewModelClass: Class<MainActivityVM> = MainActivityVM::class.java
    override val layoutId: Int = R.layout.company_warrants
    override val observeLiveData: MainActivityVM.() -> Unit = {}

    private lateinit var mainViewModel: MainActivityVM
    private val array: ArrayList<String> = ArrayList()
    private var id: String? = null
    private var isUpdate = false


    private fun initRecyclerView(listWarrants: List<Warrant>) {
//        if (listWarrants.isEmpty()) {
//            txtNoWarrants.visibility = VISIBLE
//        } else {
//            txtNoWarrants.visibility = GONE
//        }
//        recyclerWarrants1.adapter = WarrantsAdapter(listWarrants.toMutableList(), context!!, object : WarrantsAdapter.WarrantsOnClickListener {
//            override fun onClick(id: Long) {
//                mainViewModel.getItemId().value = id.toString()
//                replaceFragmentSafely(WarrantsDetailFragment(), "DetailWarrant", false, true, R.id.fragment)
//            }
//        })
//        recyclerWarrants1.adapter.notifyDataSetChanged()
    }

    private fun subscribeToSocket(array: List<String>) {
//        mainViewModel.subscribeToSocket(array, Action1 { data -> clientMessageReceived(data) })
    }

    private fun createArrayValors(listWarrants: List<Warrant>): List<String> {
        listWarrants.forEach { item -> array.add(item.valor!!) }
        return array
    }

    private fun subscribeToItemId(id: String?) {
//        mainViewModel.getWarrantsTop(null, id, null, null, null, null, null, "true", null, null)
    }

    private fun clientMessageReceived(data: String?) {
        activity?.runOnUiThread {
            Timber.d("update warrants $data")
            try {
                uiUpdate(product = GsonBuilder().create().fromJson(data, SocketModel::class.java))
            } catch (e: JsonSyntaxException) {

            }

        }
    }

    private fun uiUpdate(product: SocketModel?) {
//        if (recyclerWarrants1 != null) {
//            (recyclerWarrants1.adapter as? WarrantsAdapter)?.updateItem(Warrant(product!!))
//        }
    }
}
