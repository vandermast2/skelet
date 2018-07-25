package com.samapps.skelet.ui.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.base.BaseVM

/**
 * Created by sergey on 10/30/17.
 */

class TabsFragmentAdapter(fm: FragmentManager, private var tabs:List<BaseFragment<BaseVM>>) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence = tabs[position].getName()

    override fun getItem(position: Int): Fragment =tabs[position]

    override fun getCount(): Int= tabs.size

}