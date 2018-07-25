package com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.juliusbaer.premarket.ui.fragments.companyFragments.AktualKursFragment
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.UnderlyingModel
import com.samapps.skelet.dataFlow.models.socketModel.SocketModel
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.news.CompanyNewsFragment
import com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.warrants.WarrantsCompanyFragment
import com.samapps.skelet.utils.MiscellaneousUtils
import com.samapps.skelet.utils.extentions.onClick
import kotlinx.android.synthetic.main.fragment_company.*
import kotlinx.android.synthetic.main.header_company.*
import timber.log.Timber


class CompanyFragment : BaseFragment<CompanyVM>() {
    override val viewModelClass: Class<CompanyVM> = CompanyVM::class.java
    override val layoutId: Int = R.layout.fragment_company
    override val observeLiveData: CompanyVM.() -> Unit = { }

    companion object {
        private val EXTRA_COLLECTION_ID = MiscellaneousUtils.getExtra("collectionId")
        private val EXTRA_COLLECTION_NAME = MiscellaneousUtils.getExtra("collectionName")
        fun newInstance(lookbook: UnderlyingModel) = with(lookbook) { newInstance(id) }
        fun newInstance(collectionId: Int? = 0
//                        ,collectionName: CharSequence? = DEFAULT_TITLE
        ) = CompanyFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_COLLECTION_ID, collectionId ?: 0)
//                putCharSequence(EXTRA_COLLECTION_NAME, collectionName ?: DEFAULT_TITLE)
            }
        }
    }

    private val collectionId by lazy {
        arguments?.getInt(EXTRA_COLLECTION_ID)
                ?: 0
    }

    private var productId: String? = collectionId.toString()
    private var isImgStarTrue = false
    private var isUpdate = false
//    private lateinit var chartDataResultViewModel: ChartDataResultViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        containerUnderluingDetail.onClick {}

//        tabBarCompany.setupWithViewPager(setupViewPager())
//        tabBarCompany.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                tab.toString()
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                tab.toString()
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab?.position == 2) {
////                    if (!viewModel.isConfirmed()) {
////                        context?.startActivity(Intent(context, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
////                    }
//                }
//            }
//
//        })
//        changeTabsFont()
//        imgPhone.onClick { callToTrader(viewModel.getPhoneNumber()) }
//        imgBell.onClick {
//            viewModel.getItemId().observe(this, Observer { it ->
//                startActivity(Intent(context, AlertUnderlyingActivity::class.java).putExtra("productId", it!!.toInt()))
//            })
//        }
        btnBack.onClick { activity?.onBackPressed() }
//        activity?.toolbar?.visibility = View.GONE
        imgStar.onClick {
            setAlertState(viewModel)

        }
    }
    private fun clientMessageReceived(messageBody: String) {
        activity?.runOnUiThread {
            Timber.d("update smi index $messageBody")
            try {
                val product = GsonBuilder().create().fromJson(messageBody, SocketModel::class.java)
                if (product?.id.toString() == productId) {
                    if (!product.title.isNullOrEmpty()) name.text=product.title
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
//        viewModel.getItemId().observe(this, Observer { id ->
//            if (!isUpdate) {
//                productId = id
//                isUpdate = true
//            }
//
//            viewModel.subscribeToSocket()
//                    .subscribe(
//                            { item ->
//                                clientMessageReceived(item)
//                                Timber.d("Socket connect $item")
//                            }
//                            , { e -> Timber.e("Socket error $e") })
//            chartDataResultViewModel.getItem(productId!!)
//            chartDataResultViewModel.getUnderluingResult().observe(this, Observer { item ->
//                if (item?.error != null) {
//                    parseError(item)
//                } else {
//                    name.text = (item!!.data as UnderlyingById).name
//                    isImgStarTrue = (item.data as UnderlyingById).isInWatchList!!
//                    if (isImgStarTrue) {
//                        imgStar.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_watchlist_active))
//                    }
//                }
//            })
//        })
    }

//    private fun setupViewPager(): ViewPager {
//        viewPager.adapter = TabsFragmentAdapter(childFragmentManager, setTabs())
//        return viewPager
//    }
//
//    private fun changeTabsFont() {
//        (0 until tabBarCompany.tabCount).forEach { j ->
//            val tv: TextView = LayoutInflater.from(context).inflate(R.layout.tab_lay_title, null) as TextView
//            tv.typeface = ResourcesCompat.getFont(context!!, R.font.verlag_light)
//            tabBarCompany.getTabAt(j)!!.customView = tv
//        }
//    }

    private fun setTabs(): ArrayList<Any> = arrayListOf(AktualKursFragment(), WarrantsCompanyFragment(), CompanyNewsFragment())

    private fun setAlertState(viewModel: CompanyVM) {
//        if (!isImgStarTrue) {
//            if (viewModel.isTokenValid()) {
//                viewModel.addWatchListItem(productId!!)
//                viewModel.addWatchListItemResponse().observe(this, Observer { itr ->
//                    if (itr?.error != null) {
//                        parseError(itr)
//                    } else {
//                        if (!isImgStarTrue) {
//                            imgStar.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_watchlist_active))
//                        }
//                        isImgStarTrue = true
//                    }
//                })
//            } else {
//                Toast.makeText(context, "Is not authorized", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            viewModel.deleteWatchListItemResponse(productId!!)
//            viewModel.deleteWatchListItem().observe(this, Observer { it ->
//                isImgStarTrue = false
//                imgStar.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_watchlist))
//            })
//        }
    }

}


