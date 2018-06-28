package com.samapps.skelet.ui.main.fragments

import com.samapps.skelet.R.layout.main_fragment
import com.samapps.skelet.ui.base.BaseFragment
import com.samapps.skelet.ui.main.MainViewModel

class MainFragment : BaseFragment<MainViewModel>() {
    override val layoutId: Int = main_fragment
    override val observeLiveData: MainViewModel.() -> Unit = {}
    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    companion object {
        fun newInstance() = MainFragment()
    }

}
