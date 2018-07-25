package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName

data class ExtremeWarrantModel (
        @SerializedName("ProductId") val productId: Long?,
        @SerializedName("UnderlyingId") val underlyingId: Long?,
        @SerializedName("ContractOption") val contractOption: String?,
        @SerializedName("StrikePriceMin") val strikePriceMin: Double,
        @SerializedName("StrikePriceMax") val strikePriceMax: Double,
        @SerializedName("DistanceToStrikeMin") val distanceToStrikeMin: Double,
        @SerializedName("DistanceToStrikeMax") val distanceToStrikeMax: Double,
        @SerializedName("TopOnly") val topOnly: Boolean,
        @SerializedName("MaturityStartDate") val maturityStartDate: Long,
        @SerializedName("MaturityEndDate") val maturityEndDate: Long,
        @SerializedName("PageNumber") val pageNumber: Int,
        @SerializedName("PageSize") val pageSize: Int,
        @SerializedName("SortField") val sortField: String,
        @SerializedName("IsAscending") val isAscending: Boolean
)