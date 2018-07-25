package com.samapps.skelet.utils.extentions

import android.widget.TextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sergey on 11/8/17.
 */
fun TextView.setNumber(value: Double?) {

    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = '\''
    val dfDecimal = DecimalFormat("###########0")
//        val dfDecimal = DecimalFormat("###########0.00###")
    dfDecimal.decimalFormatSymbols = symbols
    dfDecimal.groupingSize = 3
    dfDecimal.isGroupingUsed = true
    this.text = dfDecimal.format(value)

}

fun TextView.setNumber(value: Double?, currency: String?) {

    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = '\''
    val dfDecimal = DecimalFormat("###########0")
    dfDecimal.decimalFormatSymbols = symbols
    dfDecimal.groupingSize = 3
    dfDecimal.isGroupingUsed = true
    this.text = dfDecimal.format(value) + " $currency"

}

fun TextView.setDate(date: Long?, format: String?) {
    val df = SimpleDateFormat(format, Locale.ENGLISH)
    this.text = df.format(date?.times(1000))
}

fun TextView.setDate(date: Long?, format: String?, label: String) {
    val df = SimpleDateFormat(format, Locale.ENGLISH)
    this.text = "$label ${df.format(date?.times(1000))}"
}


fun Double.format(digits: Int?) = java.lang.String.format(Locale.US, "%.${digits}f", this)

fun Double.format(digits: Int?, currency: String?) = java.lang.String.format(Locale.US, "%.${digits}f $currency", this)