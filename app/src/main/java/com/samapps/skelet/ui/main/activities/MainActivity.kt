package com.samapps.skelet.ui.main.activities

import android.os.Bundle
import com.samapps.skelet.AppApplication
import com.samapps.skelet.R
import com.samapps.skelet.ui.base.BaseActivity
import com.samapps.skelet.ui.main.fragments.main.MainViewModel
import com.samapps.skelet.ui.main.fragments.main.MainFragment

class MainActivity : BaseActivity<MainViewModel>() {
    override val observeLiveData: MainViewModel.() -> Unit = { }
    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java
    override val layoutId: Int = R.layout.main_activity
    override val containerId: Int = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.component.inject(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
