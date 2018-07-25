package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 12/7/17.
 */
data class UserInfoModel(
		@SerializedName("IsNotificationForAlertsEnable") var isNotificationForAlertsEnable: Boolean?, //true
		@SerializedName("TraderPhoneNumber") var traderPhoneNumber: String? //true
)