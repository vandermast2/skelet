package com.samapps.skelet.ui.base

import androidx.core.content.ContextCompat
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.CandleStickModel
import com.samapps.skelet.utils.extentions.onClick
import kotlinx.android.synthetic.main.fragment_smi.*
import kotlinx.android.synthetic.main.header_smi.*

abstract class BaseUnderlyingFragment<T : BaseVM>:BaseFragment<T>() {
    fun setBoxesIcon() {
        if (viewModel.getBoxes()) {
            imgBoxes.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_candlestick))
            hidePercentsHeader()
        } else {
            imgBoxes.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_boxes))
            setPercentsHeader()
        }
    }

    fun onClickBoxes() {
        imgBoxes.onClick {
            if (viewModel.getBoxes()) {
                viewModel.setBoxes(false)
            } else {
                viewModel.setBoxes(true)
            }
            setRecyclerAutomatically()
        }
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