package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName

data class CandleStickModel(@SerializedName("High")
                            val high: Double = 0.0,
                            @SerializedName("Last")
                            val last:  Double = 0.0,
                            @SerializedName("Low")
                            val low:  Double = 0.0,
                            @SerializedName("LastTraded")
                            val lastTraded:  Double = 0.0,
                            @SerializedName("Increasing")
                            val increasing: Boolean = false,
                            @SerializedName("PriceChangePct")
                            val priceChangePct:  Double = 0.0,
                            @SerializedName("Title")
                            val title: String = "",
                            @SerializedName("ProductId")
                            val productId: Int = 0,
                            @SerializedName("Open")
                            val open:  Double = 0.0)