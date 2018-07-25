package com.samapps.skelet.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.CandleStickModel
import com.samapps.skelet.ui.customView.CandleStickChart.const.PERCENT_COEFICIENT
import kotlinx.android.synthetic.main.candle_stick_card.view.*

class CandleStickChart : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var max: Int = 0

    object const {
        const val PERCENT_COEFICIENT: Int = 100

    }

    private fun getCoefficient(): Float = (max * 100) / (175 / 2).toFloat()

    init {
        LayoutInflater.from(context).inflate(R.layout.candle_stick_card, this, true)
    }

    private fun setSizeHighLow(low: Double, hidh: Double) {
        highLow.layoutParams.width = convertDpToPx(getWidth(low, hidh), context).toInt()
    }

    private fun setSizeOpened(opened: Double, last: Double) {
        imgOpen.layoutParams.width = convertDpToPx(getWidth(opened, last), context).toInt()
    }

    private fun getWidth(low: Double, high: Double): Float {
        return when {
            high > low -> ((high - low) * PERCENT_COEFICIENT / getCoefficient()).toFloat()
            high < low -> ((low - high) * PERCENT_COEFICIENT / getCoefficient()).toFloat()
            else -> 1f
        }
    }

    private fun setPositionHighLow(view: View, positionX: Double) {
        view.animate().translationX(convertDpToPx(positionX.toFloat(), context))
    }

    fun initialize(candleStickModel: CandleStickModel, max: Int) {
        this.max = max
        imgOpen.layoutParams.width = 0
        highLow.layoutParams.width = 0
        setIncreasingColor(candleStickModel)
        setColorProgress(candleStickModel.priceChangePct)
        setPositionHighLow(candleStickModel)
        setPositionOpen(candleStickModel)
        setPositionHighLow(imgLast, candleStickModel.last * PERCENT_COEFICIENT / getCoefficient())
        setSizeHighLow(candleStickModel.low, candleStickModel.high)
        setSizeOpened(candleStickModel.open, candleStickModel.last)
    }

    private fun setPositionOpen(candleStickModel: CandleStickModel) {
        if (candleStickModel.open < candleStickModel.last) {
            setPositionHighLow(imgOpen, candleStickModel.open * PERCENT_COEFICIENT / getCoefficient())
        } else {
            setPositionHighLow(imgOpen, candleStickModel.last * PERCENT_COEFICIENT / getCoefficient())
        }
    }

    private fun setPositionHighLow(candleStickModel: CandleStickModel) {
        if (candleStickModel.low < candleStickModel.high) {
            setPositionHighLow(highLow, candleStickModel.low * PERCENT_COEFICIENT / getCoefficient())
        } else {
            setPositionHighLow(highLow, candleStickModel.high * PERCENT_COEFICIENT / getCoefficient())
        }
    }

    private fun setColorProgress(priceChangePct: Double) {
        if (priceChangePct < 0) {
            imgOpen.setBackgroundColor(ContextCompat.getColor(context, R.color.rouge))
            imgLast.setBackgroundColor(ContextCompat.getColor(context, R.color.rouge))
        } else {
            imgOpen.setBackgroundColor(ContextCompat.getColor(context, R.color.blueGreen))
            imgLast.setBackgroundColor(ContextCompat.getColor(context, R.color.blueGreen))
        }
    }

    private fun setIncreasingColor(candleStickModel: CandleStickModel) {
        when {
            candleStickModel.increasing -> imgLast.setBackgroundColor(ContextCompat.getColor(context, R.color.blueGreen))
            !candleStickModel.increasing and (candleStickModel.priceChangePct > 0) -> imgLast.setBackgroundColor(ContextCompat.getColor(context, R.color.blueGreen))
            !candleStickModel.increasing and (candleStickModel.priceChangePct < 0) -> imgLast.setBackgroundColor(ContextCompat.getColor(context, R.color.rouge))
        }
    }

    private fun convertPixelsToDp(px: Float, context: Context): Float =
            px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    private fun convertDpToPx(dp: Float, context: Context) =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)

}