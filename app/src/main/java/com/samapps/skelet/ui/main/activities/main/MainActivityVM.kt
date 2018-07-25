package com.samapps.skelet.ui.main.activities.main

import androidx.lifecycle.MutableLiveData
import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.managers.IDataManager
import com.samapps.skelet.ui.base.BaseVM
import javax.inject.Inject

class MainActivityVM : BaseVM() {

    @Inject
    lateinit var dataManger: IDataManager

    private var mCurrentName: MutableLiveData<String>? = null

    init {
        AppApplication.component.inject(this)
    }

    fun getItemId(): MutableLiveData<String> {
        if (mCurrentName == null) {
            mCurrentName = MutableLiveData()
        }
        return mCurrentName as MutableLiveData<String>
    }

    fun isFirstTime(): Boolean = dataManger.getFirstTimeLoading()

    fun setFirstTimeValue() {
        dataManger.setFirstTimeLoading(true)
    }

    fun getPhoneNumber(): String = dataManger.getPhoneNumber()

    fun savePhoneNumber(number: String) {
        dataManger.savePhoneNumber(number)
    }

    fun getItem(itemId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}