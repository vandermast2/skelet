package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName
import com.samapps.skelet.dataFlow.models.socketModel.SocketModel


/**
 * Created by sergey on 12/7/17.
 */
data class Warrant(
        @SerializedName("StrikeType") var strikeType: String?, //Call
        @SerializedName("Ticker") var ticker: String?, //ABBAJB
        @SerializedName("PriceBid") var priceBid: Double?, //0.32
        @SerializedName("PriceAsk") var priceAsk: Double?, //0.33
        @SerializedName("PriceChangePct") var priceChangePct: Double?, //-0.08702115
        @SerializedName("Id") var id: Long?, //247
        @SerializedName("IsTop") var isTop: Boolean?, //247
        @SerializedName("Valor") var valor: String?, //34855089
        @SerializedName("Title") var title: String?, //Call Warrants on ABB
        @SerializedName("StrikeLevel") var strikeLevel: Double?, //160
        @SerializedName("ExerciseDate") var exerciseDate: Long? //1521158400
) {
    constructor(product: SocketModel) : this(isTop=false,exerciseDate= product.priceDateTime,strikeLevel=0.0,priceBid = product.priceBid, priceAsk = product.priceAsk, strikeType = null, priceChangePct = product.priceChangePct, id = product.id?.toLong(), valor = product.valor, ticker = product.identifier, title = product.identifier)
}

