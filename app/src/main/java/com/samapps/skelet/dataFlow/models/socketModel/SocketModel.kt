package com.samapps.skelet.dataFlow.models.socketModel

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 12/7/17.
 */
data class SocketModel(
        @SerializedName("Id") var id: Int?, //0
        @SerializedName("Identifier") var identifier: String?, //SMIM
        @SerializedName("Isin") var isin: String?, //CH0019399838
        @SerializedName("Ticker") var ticker: String?, //CH0019399838
        @SerializedName("Title") var title: String?, //CH0019399838
        @SerializedName("ProductType") var productType: String?,
        @SerializedName("Valor") var valor: String?, //1939983
        @SerializedName("PriceAsk") var priceAsk: Double?, //0.0
        @SerializedName("PriceAskVolume") var priceAskVolume: Double?, //0.0
        @SerializedName("PriceCurrency") var priceCurrency: String?, //CH
        @SerializedName("Open") var priceOpen: Double?, //9583.14
        @SerializedName("PriceBid") var priceBid: Double?, //0.0
        @SerializedName("PriceBidAskSpreadPct") var priceBidAskSpreadPct: Double?, //0.0
        @SerializedName("PriceBidVolume") var priceBidVolume: Double?, //0.0
        @SerializedName("PriceSettled") var priceSettled: String?, //2667,391
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //0.17
        @SerializedName("PriceChangeAbs") var priceChangeAbs: Double?, //4.663
        @SerializedName("ImpliedVolatility") var impliedVolatility: Double?, //0.0
        @SerializedName("PriceDateTime") var priceDateTime: Long?, //2018-01-09T13:46:13+01:00
        @SerializedName("LastTraded") var lastTraded: Double?, //2672.054
        @SerializedName("MinLastTraded") var minLastTraded: Double?, //2672.054
        @SerializedName("MaxLastTraded") var maxLastTraded: Double?, //2672.054
        @SerializedName("DistanceToStrikePct") var distanceToStrikePct: Double?,
        @SerializedName("Gearing") var gearing: Double?,
        @SerializedName("Top") val top: Boolean = false,
        @SerializedName("Leverage") val leverage: Double?,
        @SerializedName("Delta") val delta: Double?,
        @SerializedName("TradedVolume") val tradedVolume: Double?
)