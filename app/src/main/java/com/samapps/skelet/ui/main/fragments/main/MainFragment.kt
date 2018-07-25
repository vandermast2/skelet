package com.samapps.skelet.ui.main.fragments.main

import android.os.Bundle
import android.view.View
import android.widget.ListPopupWindow
import com.google.android.material.tabs.TabLayout
import com.samapps.skelet.R
import com.samapps.skelet.ui.adapters.ItemMenu
import com.samapps.skelet.ui.adapters.ListPopupWindowAdapter
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.fragments.main.underluings.smi.SMIFragment
import kotlinx.android.synthetic.main.fragment_home_market.*

class MainFragment : BaseFragment<MainViewModel>() {
    override fun getName(): String = ""
    override val layoutId: Int = R.layout.fragment_home_market
    override val observeLiveData: MainViewModel.() -> Unit = {}
    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popupWindow = ListPopupWindow(context)
        tabBarHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val tabPosition = tab?.position!!
                val fragmentManager = fragmentManager
                when (tabPosition) {
                    0 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, SMIFragment())?.commit()
//                    1 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, MidcapMarketFragment())?.commit()
//                    2 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, InternationalMarketsFragment())?.commit()
//                    3 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, WatchlistFragment())?.commit()
                    4 -> showPopupMenu(frame)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab.toString()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabPosition = tab?.position!!
                val fragmentManager = fragmentManager
                when (tabPosition) {
                    0 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, SMIFragment())?.commit()
//                    1 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, MidcapMarketFragment())?.commit()
//                    2 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, InternationalMarketsFragment())?.commit()
//                    3 -> fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, WatchlistFragment())?.commit()
                    4 -> showPopupMenu(frame)
                }

                if (tabPosition == 3) {
//                    viewModel.getWatchListAnswer()
//                    viewModel.getWatchList().observe(this@MainFragment, Observer { if (it?.error != null) parseError(it) })
//                    if (!viewModel.isConfirmed()) {
//                        context?.startActivity(Intent(context, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
//                    }
                }
            }

        })
        changeTabsFont(tabBarHome, 9f)
        tabBarHome.getTabAt(0)?.select()
    }

    private fun showPopupMenu(v: View) {
        if (popupWindow?.isShowing!!) {
            popupWindow?.dismiss()
        } else {
            val adapter = ListPopupWindowAdapter(context!!,
                    arrayListOf(
                            ItemMenu("News", R.drawable.news_tab_btn),
                            ItemMenu("Warrants", R.drawable.warrants_tab_btn),
                            ItemMenu("Alerts", R.drawable.alerts_tab_btn),
                            ItemMenu("Settings", R.drawable.settings_tab_btn),
                            ItemMenu("Contact", R.drawable.contacts_tab_btn),
                            ItemMenu("Disclaimer", R.drawable.disclaimer_tab_btn))
            )
            popupWindow?.anchorView = v
            popupWindow?.setAdapter(adapter)
            popupWindow?.width = convertDpToPixel(211, context!!)
//        popupWindow?.setBackgroundDrawable(ContextCompat.getDrawable(context!!, android.R.color.transparent))
            popupWindow?.setOnItemClickListener { parent, view, position, id ->
                when (position) {
//                    0 -> {
//                        replaceFragmentSafely(NewsFragment(), Constants.NEWS_FRAGMENT_TAG, false, true, R.id.containerLayout)
//                        popupWindow?.dismiss()
//                    }
//                    1 -> {
//                        replaceFragmentSafely(WarrantsFragment(), Constants.WARRANTS_FRAGMENT_TAG, false, true, R.id.containerLayout)
//                        popupWindow?.dismiss()
//                    }
//                    2 -> {
//                        replaceFragmentSafely(AlertsFragment(), Constants.ALERT_FRAGMENT_TAG, false, true, R.id.containerLayout)
//                        popupWindow?.dismiss()
//                    }
//                    3 -> {
//                        replaceFragmentSafely(ChildSettingsFragment(), Constants.SETTINGS_FRAGMENT_TAG, false, true, R.id.containerLayout)
//                        popupWindow?.dismiss()
//                    }
//                    4 -> {
//                        replaceFragmentSafely(ContactFragment(), Constants.CONTACT_FRAGMENT_TAG, false, true, R.id.containerLayout)
//                        popupWindow?.dismiss()
//                    }
//                    5 -> {
//                        replaceFragmentSafely(DisclaimerFragment(), Constants.DISCLAIMER_FRAGMENT_TAG, false, true, R.id.containerLayout)
//                        popupWindow?.dismiss()
//                    }
                }
                popupWindow?.dismiss()
            }
            popupWindow?.show()
        }

    }

}
