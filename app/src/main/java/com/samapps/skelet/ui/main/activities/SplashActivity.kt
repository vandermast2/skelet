package com.samapps.skelet.ui.main.activities

import android.os.Bundle
import android.view.View
import com.samapps.skelet.R
import com.samapps.skelet.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_fullscreen.*

class SplashActivity : BaseActivity<SplashActivityVM>() {

    override val viewModelClass: Class<SplashActivityVM> = SplashActivityVM::class.java
    override val layoutId: Int = R.layout.activity_fullscreen
    override val containerId: Int = 0
    override val observeLiveData: SplashActivityVM.() -> Unit={}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullscreen_content.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        setContentView(R.layout.activity_fullscreen)



    }
}
