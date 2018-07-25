package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName
import com.samapps.skelet.dataFlow.models.socketModel.SocketModel


/**
 * Created by sergey on 12/22/17.
 */
data class Index(
        @SerializedName("Name") var name: String?, //Swiss Market Index (SMI®)
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //0.43
        @SerializedName("LastTraded") var lastTraded: Double?, //9583.14
        @SerializedName("PriceSettled") var priceSettled: Double?, //9583.14
        @SerializedName("Open") var priceOpen: Double?, //9583.14
        @SerializedName("MinLastTraded") var minLastTraded: Double?, //0
        @SerializedName("MaxLastTraded") var maxLastTraded: Double?, //0
        @SerializedName("PriceCurrency") var priceCurrency: String?, //CHF
        @SerializedName("Id") var id: Int?, //53
        @SerializedName("Valor") var valor: String?, //998089
        @SerializedName("Ticker") var ticker: String?, //SMI
        @SerializedName("Title") var title: String?,//JB SMI
        @SerializedName("MarketsTitle") var marketsTitle: String?,//JB SMI
        @SerializedName("PriceDateTime") var date: Long?, //ABB
        @SerializedName("PriceBidVolume") var priceBidVolume: Double?, //0
        @SerializedName("PriceAskVolume") var priceAskVolume: Double?, //0
        @SerializedName("InitialReferencePrice") var initialReferencePrice: Double?,
        @SerializedName("ImpliedVolatility") var impliedVolatility: Double?, //0
        @SerializedName("TradedVolume") var tradedVolume: Double?,//0
        @SerializedName("HasDetails") var hasDetails: Boolean? //0
) {
    constructor(product: SocketModel) : this(
            hasDetails = false,
            marketsTitle = "",
            priceSettled = 0.0,
            tradedVolume = 0.0,
            minLastTraded = 0.0,
            maxLastTraded = 0.0,
            priceCurrency = "",
            initialReferencePrice = 0.0,
            impliedVolatility = 0.0,
            priceBidVolume = 0.0,
            priceAskVolume = 0.0,
            priceOpen = product.priceOpen,
            priceChangePct = product.priceChangePct,
            lastTraded = product.lastTraded,
            name = product.identifier,
            id = product.id,
            valor = product.valor,
            ticker = product.identifier,
            title = product.identifier,
            date = product.priceDateTime)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Index

        if (valor != other.valor) return false

        return true
    }

    override fun hashCode(): Int {
        return valor?.hashCode() ?: 0
    }
}


