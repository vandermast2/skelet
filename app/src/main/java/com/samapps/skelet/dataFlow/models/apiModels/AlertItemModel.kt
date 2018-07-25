package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 1/2/18.
 */

data class AlertItemModel(
        @SerializedName("Id") var id: Int?, //0
        @SerializedName("ProductId") var productId: Int?, //0
        @SerializedName("Criterion") var criterion: String?, //string
        @SerializedName("MinValue") var minValue: Double?, //0
        @SerializedName("MaxValue") var maxValue: Double?, //0
        @SerializedName("Value") var value: Double? //0
)