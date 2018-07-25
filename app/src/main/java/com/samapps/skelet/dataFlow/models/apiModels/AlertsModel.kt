package com.samapps.skelet.dataFlow.models.apiModels
import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 1/11/18.
 */
data class AlertsModel(
		@SerializedName("ProductId") var productId: String?, //5
		@SerializedName("ProductName") var productName: String?, //Banco Santander SA
		@SerializedName("IsUnderlying") var isUnderlying: Boolean? //true
)