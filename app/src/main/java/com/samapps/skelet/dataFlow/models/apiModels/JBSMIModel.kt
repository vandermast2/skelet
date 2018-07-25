package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName
import com.samapps.skelet.dataFlow.models.socketModel.SocketModel


/**
 * Created by sergey on 12/7/17.
 */

data class JBSMIModel(
        @SerializedName("LastTraded") var lastTraded: Double?, //26.87
        @SerializedName("Name") var name: String?, //ABB Ltd
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //-0.297
        @SerializedName("PriceAsk") var priceAsk: Double?, //26.87
        @SerializedName("PriceBid") var priceBid: Double?, //26.86
        @SerializedName("Id") var id: Int?, //73
        @SerializedName("Valor") var valor: String?, //1222171
        @SerializedName("Ticker") var ticker: String?, //ABBN SW
        @SerializedName("Title") var title: String? //ABB

) {
    constructor(product: SocketModel) : this(priceChangePct = product.priceChangePct, priceAsk = product.priceAsk, priceBid = product.priceBid, lastTraded = product.lastTraded, name = product.identifier, id = product.id, valor = product.valor, ticker = product.identifier, title = product.identifier)
    constructor(midcap: JBMidCapModel): this(midcap.lastTraded, midcap.name, midcap.priceChangePct, midcap.priceAsk, midcap.priceBid, midcap.id, midcap.valor, midcap.ticker, midcap.title)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JBSMIModel

        if (valor != other.valor) return false

        return true
    }

    override fun hashCode(): Int {
        return valor?.hashCode() ?: 0
    }

}

