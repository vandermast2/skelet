package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by sergey on 12/7/17.
 */
data class UnderlyingModel (

    @SerializedName("Name")
    @Expose
    var name: String? = null,
    @SerializedName("PriceAsk")
    @Expose
    var priceAsk: Int = 0,
    @SerializedName("PriceBid")
    @Expose
    var priceBid: Int = 0,
    @SerializedName("PriceChangePct")
    @Expose
    var priceChangePct: Int = 0,
    @SerializedName("Id")
    @Expose
    var id: Int = 0

)