package com.alexandresantos.nodelightning.features.nodelist.data.models

import com.google.gson.annotations.SerializedName

data class NodeListResponse(
    @SerializedName("publicKey") var publicKey: String,
    @SerializedName("alias") var alias: String,
    @SerializedName("channels") var channels: Int,
    @SerializedName("capacity") var capacity: Long,
    @SerializedName("firstSeen") var firstSeen: Long,
    @SerializedName("updatedAt") var updatedAt: Long,
    @SerializedName("city") var brType: BrType? = null,
    @SerializedName("country") var country: BrType? = null
)

data class BrType(
    @SerializedName("pt-BR") var ptBr: String? = null,
)