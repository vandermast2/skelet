package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName

/**
 * Created by sergey on 10/10/17.
 */
data class ChartData(
        @SerializedName("Data") var data: List<Data?>?
)

data class Data(
        @SerializedName("X") var x: Long?, //0
        @SerializedName("Y") var y: Float? //0
)