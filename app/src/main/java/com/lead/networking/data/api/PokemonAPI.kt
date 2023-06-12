package com.lead.networking.data.api

import com.lead.networking.data.api.PokemonConstant.POKEMON_LIST
import com.lead.networking.data.model.PokemonListRes
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {
    @GET(POKEMON_LIST)
    suspend fun listPokemon(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : Response<PokemonListRes>
}