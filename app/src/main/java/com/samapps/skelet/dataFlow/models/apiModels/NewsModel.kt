package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 12/7/17.
 */
data class NewsModel(
        @SerializedName("Id")
        var id: Int? = null,
        @SerializedName("Headline")
        var headLine: String? = null,
        @SerializedName("PublishDate")
        var publishDate: Long? = null,
        @SerializedName("NewsText")
        var newsText: String? = null,
        @SerializedName("WasRead")
        var isRead: Boolean = false
)