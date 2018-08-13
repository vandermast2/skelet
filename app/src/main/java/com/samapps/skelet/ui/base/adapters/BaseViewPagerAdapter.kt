package com.samapps.skelet.ui.base.adapters

import android.content.Context
import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.samapps.skelet.ui.base.BaseFragment

class BaseViewPagerAdapter(context: Context, fm: FragmentManager,
                           private val fragmentInfoContainers: List<FragmentInfoContainer>)
    : FragmentPagerAdapter(fm), ViewPager.OnPageChangeListener {

    private val appContext = context.applicationContext
    private val fragments = SparseArray<BaseFragment<*>>()

    override fun getItem(position: Int): Fragment {
        val fragmentInfoContainer = fragmentInfoContainers[position]
        return Fragment.instantiate(appContext, fragmentInfoContainer.fragmentClass.name, fragmentInfoContainer.args)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as BaseFragment<*>
        fragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        fragments.remove(position)
        super.destroyItem(container, position, any)
    }

    private fun getFragment(position: Int) = fragments[position]

    private fun onSelect(position: Int) = getFragment(position)?.onViewPagerSelect()

    override fun getPageTitle(position: Int) = fragmentInfoContainers[position].title

    override fun getCount() = fragmentInfoContainers.size

    class FragmentInfoContainer(val fragmentClass: Class<out BaseFragment<*>>,
                                val title: String = "",
                                val args: Bundle = Bundle())

    override fun onPageScrollStateChanged(state: Int) {
        // nothing
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // nothing
    }

    override fun onPageSelected(position: Int) {
        onSelect(position)
    }
}
