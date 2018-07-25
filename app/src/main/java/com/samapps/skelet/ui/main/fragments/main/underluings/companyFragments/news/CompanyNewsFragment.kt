package com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.news

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.NewsModel
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.activities.main.MainActivityVM


class CompanyNewsFragment : BaseFragment<MainActivityVM>() {
    override val viewModelClass: Class<MainActivityVM> = MainActivityVM::class.java
    override val layoutId: Int = R.layout.fragment_company_news
    override val observeLiveData: MainActivityVM.() -> Unit = {

    }

    var isAutorized: Boolean = false
    var isUpdated = false
    var id: String? = null


    private lateinit var mainModel: MainActivityVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainModel = ViewModelProviders.of(activity!!).get(MainActivityVM::class.java)
    }

    override fun onResume() {
        super.onResume()
        subscribeToNews()
    }

    private fun subscribeToNews() {
        mainModel.getItemId().observe(this, Observer {
//            if (!isUpdated) {
//                id = it
//                isUpdated = true
//            }
//            mainModel.getNewsSubscrById(id!!)
//            getNews()
        })
    }

    private fun getNews() {
//        mainModel.getNewsById().observe(this, Observer { it ->
//            if (it?.error != null) {
//                txtNoNews.visibility = View.VISIBLE
//            } else {
//                if ((it!!.data as List<NewsModel>).isEmpty()) {
//                    txtNoNews.visibility = View.VISIBLE
//                } else {
//                    txtNoNews.visibility = View.GONE
//                    with(recyclerNews) {
//                        adapter = NewsAdapter(it.data as List<NewsModel>, context!!)
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//            }
//        })
//        imgRight.visibility = View.GONE
//        imgLeft.visibility = View.GONE
//        txtTitle.visibility = View.GONE

    }
}
