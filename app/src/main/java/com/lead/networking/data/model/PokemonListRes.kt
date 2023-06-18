package com.lead.networking.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListRes(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<PokemonResultRes>? = null
) {
    data class PokemonResultRes(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("url")
        val url: String? = null,
    )
}

