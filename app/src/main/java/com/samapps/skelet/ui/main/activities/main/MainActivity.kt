package com.samapps.skelet.ui.main.activities.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import com.samapps.skelet.AppApplication
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.UnderlyingModel
import com.samapps.skelet.ui.base.BaseActivity
import com.samapps.skelet.ui.main.fragments.main.MainFragment
import com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.company.CompanyFragment
import com.samapps.skelet.utils.extentions.addFragmentSafelfy
import com.samapps.skelet.utils.extentions.replaceFragmentSafely
import timber.log.Timber

class MainActivity : BaseActivity<MainActivityVM>() {
//    private lateinit var netDialog: NoNetworkDialog
    private var bundle1: Bundle? = null

    override val observeLiveData: MainActivityVM.() -> Unit = { }
    override val viewModelClass: Class<MainActivityVM> = MainActivityVM::class.java
    override val layoutId: Int = R.layout.main_activity
    override val containerId: Int = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppApplication.component.inject(this)

//        netDialog = NoNetworkDialog()

        bundle1 = intent.extras
        if (savedInstanceState == null) {
            replaceFragmentSafely(MainFragment(),false,false,R.id.container)
        }
    }

    private fun checkNotifications(bundle: Bundle?, savedInstanceState: Bundle?) {
        if (bundle != null) {
            updateCount()
            val productType = bundle.get("ProductTypeId")
            val productId = bundle.get("ProductId")
            when (productType) {
//                "0" -> startActivity(Intent(this, DetailNewsActivity::class.java).putExtra("id", productId.toString()))
//                "1" -> { replaceFragmentSafely(IndexDetailFragment(), false, true, R.id.containerLayoutFragment, R.style.FragStyle) }
//                "2" -> {replaceFragmentSafely(CompanyFragment.newInstance(UnderlyingModel(id = productId.toString().toInt())), false, true, R.id.container, R.style.FragStyle) }
//                "3" -> {
//                    viewModel.getItemId().value = productId.toString()
//                    replaceFragmentSafely(WarrantsDetailFragment(), false, true, R.id.containerLayoutFragment)
//                }
            }
            bundle1 = null
            Timber.d("Notification productType = $productType; productId = $productId ")
        } else {
//            if (isNetworkAvailable()) {
//                openFirstTimeActivity()
//                openHomeFragment(savedInstanceState)
//                viewModel.setFirstTimeValue()
//            } else {
////                netDialog.showDialog(this)
//            }
        }

    }

    private fun openHomeFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragmentSafelfy(MainFragment(), false, false, R.id.container, R.style.FragStyle)
        }
    }

    private fun openFirstTimeActivity() {
        if (viewModel.isFirstTime()) {
//            startActivity(Intent(this, PromotionActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            try {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (viewModel.getPhoneNumber() != "00000000") {

                        startActivity(Intent(Intent.ACTION_CALL)
                                .setData(Uri.parse("tel:${viewModel.getPhoneNumber()}")))
                    }
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        overridePendingTransition(R.anim.stay, R.anim.slide_down)
        super.onBackPressed()
    }

    private fun updateCount() {
//        ShortcutBadger.applyCount(this, 0)
//        val realm = Realm.getDefaultInstance()
//        val count = realm.where(BadgeCount::class.java).findFirst()?.count?.minus(1)
//        realm.executeTransaction { it ->
//            val badgeCount = BadgeCount()
//            badgeCount.count = count
//            it.insertOrUpdate(badgeCount)
//        }
    }


}
