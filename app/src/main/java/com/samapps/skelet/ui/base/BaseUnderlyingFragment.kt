package com.samapps.skelet.ui.base

import android.view.View
import com.samapps.skelet.dataFlow.models.apiModels.CandleStickModel
import com.samapps.skelet.utils.extentions.format
import kotlinx.android.synthetic.main.fragment_smi.*

abstract class BaseUnderlyingFragment<T : BaseVM>:BaseFragment<T>() {
    fun hidePercentsHeader() {
        txtLeftPercent.visibility = View.GONE
        txtCenterLeft.visibility = View.GONE
        txtCenter.visibility = View.GONE
        txtRightPercent.visibility = View.GONE
        txtCenterRight.visibility = View.GONE
    }

    fun setPercentsHeader() {
        txtLeftPercent.visibility = View.VISIBLE
        txtCenterLeft.visibility = View.VISIBLE
        txtCenter.visibility = View.VISIBLE
        txtRightPercent.visibility = View.VISIBLE
        txtCenterRight.visibility = View.VISIBLE
    }


    fun getMaxPositiveValue(list: List<CandleStickModel>): Int {
        return when {
            (-1 * list.minBy { it.priceChangePct }?.priceChangePct!!.times(100)) > list.maxBy { it.priceChangePct }?.priceChangePct!!.times(100) -> (-1 * list.minBy { it.priceChangePct }?.priceChangePct!!.times(100)).toInt() + 1
            (-1 * list.minBy { it.priceChangePct }?.priceChangePct!!.times(100)) < list.maxBy { it.priceChangePct }?.priceChangePct!!.times(100) -> list.maxBy { it.priceChangePct }?.priceChangePct!!.times(100).toInt() + 1
            else -> 1
        }
    }

    fun setValuesOnHeader(value: Int) {
        val maxValue: Int = value
        val minValue: Int = (-1 * value)
        txtLeftPercent.text = "$minValue %"
        txtRightPercent.text = "$maxValue %"
        txtCenterLeft.text = (minValue.toDouble() / 2).format(1, "%%")
        txtCenterRight.text = (maxValue.toDouble() / 2).format(1, "%%")
    }
}