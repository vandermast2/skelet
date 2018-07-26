package com.samapps.skelet.ui.main.activities.splash

import android.content.Intent
import androidx.lifecycle.Observer
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.RegistrationModel
import com.samapps.skelet.ui.base.BaseActivity
import com.samapps.skelet.ui.main.activities.main.MainActivity
import com.samapps.skelet.utils.HardwareIdProvider
import org.jetbrains.anko.doAsync
import timber.log.Timber

class SplashActivity : BaseActivity<SplashActivityVM>() {
    override val viewModelClass: Class<SplashActivityVM> = SplashActivityVM::class.java
    override val layoutId: Int = R.layout.activity_fullscreen
    override val containerId: Int = 0
    override val observeLiveData: SplashActivityVM.() -> Unit = {
        if (isFirstTime()) {
            setUserId(HardwareIdProvider(this@SplashActivity).deviceId)
        }
        getRegistrationToken().observe(this@SplashActivity, Observer {
            if (it?.error != null) {
                Timber.e(it.error.message)
            } else {
                doAsync {
                    val result = saveToken(it.data!!)
                    runOnUiThread {
                        if (result){
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }

            }
        })
    }

    private fun saveToken(it:RegistrationModel):Boolean{
        viewModel.saveToken(it.accessToken!!)
        viewModel.savePublicKey(it.publicKey!!)
        viewModel.saveTokenRole(it.role!!)
        if (it.role == "Subscriber") {
            viewModel.setIsConfirmed(true)
        } else {
            viewModel.setIsConfirmed(false)
        }
        return true
    }
}

