package com.samapps.skelet.dataFlow.models.apiModels
import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 12/27/17.
 */

data class DetailWarrant(
        @SerializedName("PriceBid") var priceBid: Double?, //0
        @SerializedName("PriceAsk") var priceAsk: Double?, //0
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //0
        @SerializedName("PiceAskVolume") var piceAskVolume: Double?, //0
        @SerializedName("PriceBidVolume") var priceBidVolume: Double?, //0
        @SerializedName("PriceChangeAbs") var priceChangeAbs: Double?, //0
        @SerializedName("PriceSettled") var priceSettled: Double?, //0
        @SerializedName("StrikeLevel") var strikeLevel: Double?, //750
        @SerializedName("ExerciseDate") var exerciseDate: Long?, //2017-12-15T00:00:00
        @SerializedName("Ratio") var ratio: String?, //0
        @SerializedName("ImpliedVolatility") var impliedVolatility: Double?, //0
        @SerializedName("Delta") var delta: Double?, //0
        @SerializedName("PriceCurrency") var priceCurrency: String?, //CHF
        @SerializedName("Leverage") var leverage: Double?, //0
        @SerializedName("Gearing") var gearing: Double?, //0
        @SerializedName("LastTraded") var lastTraded: Double?, //0
        @SerializedName("DistanceToStrikePct") var distanceToStrikePct: Double?, //0
        @SerializedName("TradedVolume") var tradedVolume: Double?, //0
        @SerializedName("PriceDateTime") var priceDateTime: Long?, //0001-01-01T00:00:00
        @SerializedName("IsInWatchList") var isInWatchList: Boolean?, //false
        @SerializedName("Id") var id: Int?, //37
        @SerializedName("Valor") var valor: String?, //34076915
        @SerializedName("Ticker") var ticker: String?, //GOODJB
        @SerializedName("Title") var title: String?, //GOODJB
        @SerializedName("Name") var name: String? //GOODJB
)