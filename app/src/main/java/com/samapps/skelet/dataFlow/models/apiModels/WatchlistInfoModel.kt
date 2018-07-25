package com.samapps.skelet.dataFlow.models.apiModels
import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 1/26/18.
 */

data class WatchlistInfoModel(
		@SerializedName("Warrants") var warrants: List<Products?>?,
		@SerializedName("Underlyings") var underlyings: List<Products?>?
)

data class Products(
        @SerializedName("LastTraded") var lastTraded: Double?, //0
        @SerializedName("NotificationReceived") var isNotification: Boolean?, //0
        @SerializedName("StrikeType") var strikeType: String?, //string
        @SerializedName("StrikeLevel") var strikeLevel: Double?, //string
        @SerializedName("ExerciseDate") var date: Long?, //string
        @SerializedName("Ticker") var ticker: String?, //string
        @SerializedName("PriceBid") var priceBid: Double?, //0
        @SerializedName("PriceAsk") var priceAsk: Double?, //0
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //0
        @SerializedName("Id") var id: Int?, //0
        @SerializedName("Valor") var valor: String?, //string
        @SerializedName("Title") var title: String? //string
)

