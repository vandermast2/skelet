package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 2/15/18.
 */

data class PromotionInfoModel(
        @SerializedName("Title") var title: String?,
        @SerializedName("Description") var description: String?,
        @SerializedName("Link") var link: String?,
        @SerializedName("ImageUrl") var imageUrl: String?,
        @SerializedName("LastModifiedDate") var lastModifiedDate: Long?,
        @SerializedName("FileHash") var fileHash: String?
)