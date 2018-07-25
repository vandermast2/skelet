package com.samapps.skelet.dataFlow.models.apiModels
import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 1/11/18.
 */


data class AlertSendModel(
        @SerializedName("Id") val Id: Int?, //0
//        @SerializedName("ProductId") var productId: Int?, //0
        @SerializedName("Criterion") var criterion: String?,
        @SerializedName("Value") var value: String?
)
