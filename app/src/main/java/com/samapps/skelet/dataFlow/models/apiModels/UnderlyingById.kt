package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 12/7/17.
 */

data class UnderlyingById(
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //-0.45
        @SerializedName("PriceAsk") var priceAsk: Double?, //8300
        @SerializedName("PriceBid") var priceBid: Double?, //8295
        @SerializedName("MinLastTraded") var minLastTraded: Double?, //8295
        @SerializedName("MaxLastTraded") var maxLastTraded: Double?, //8295
        @SerializedName("PriceAskVolume") var priceAskVolume: Double?, //3
        @SerializedName("PriceBidVolume") var priceBidVolume: Double?, //4
        @SerializedName("PriceChangeAbs") var priceChangeAbs: Double?, //-37.5
        @SerializedName("PriceDateTime") var priceDateTime: Long?, //-37.5
        @SerializedName("PriceSettled") var priceSettled: Double?, //-37.5
        @SerializedName("Open") var priceOpen: Double?, //9583.14
        @SerializedName("LastTraded") var lastTraded: Double?, //-37.5
        @SerializedName("InitialReferencePrice") var initialReferencePrice: Double?, //6705
        @SerializedName("ImpliedVolatility") var impliedVolatility: Double?, //0
        @SerializedName("PriceCurrency") var priceCurrency: String?, //CHF
        @SerializedName("TopWarrantsCount") var topWarrantsCount: Int?, //0
        @SerializedName("IsInWatchList") var isInWatchList: Boolean?, //false
        @SerializedName("Id") var id: Int?, //120
        @SerializedName("Valor") var valor: String?, //58797
        @SerializedName("Ticker") var ticker: String?, //SIK SW
        @SerializedName("Title") var title: String?, //Sika
        @SerializedName("Name") var name: String? //Sika
)