package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName


/**
 * Created by sergey on 12/7/17.
 */
data class UnderlyingInfoModel(
		@SerializedName("PriceAskVolume") var priceAskVolume: Double?, //285
		@SerializedName("PriceBidVolume") var priceBidVolume: Double?, //98
		@SerializedName("PriceChangeAbs") var priceChangeAbs: Double?, //-0.88
		@SerializedName("InitialReferencePrice") var initialReferencePrice: Double?, //123.53
		@SerializedName("ImpliedVolatility") var impliedVolatility: Double?, //0
		@SerializedName("PriceCurrency") var priceCurrency: String?, //EUR
		@SerializedName("Name") var name: String?, //Bayer AG
		@SerializedName("PriceChangePct") var priceChangePct: Double?, //-0.814
		@SerializedName("PriceAsk") var priceAsk: Double?, //107.26
		@SerializedName("PriceBid") var priceBid: Double?, //107.22
		@SerializedName("Id") var id: Long?, //27
		@SerializedName("Valor") var valor: String?, //10367293
		@SerializedName("Ticker") var ticker: String?, //BAYN GY
		@SerializedName("Title") var title: String?, //Bayer
		@SerializedName("LastTraded") val lastTraded: Float
)