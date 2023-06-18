package com.lead.networking.data.remote

import com.lead.networking.data.api.PokemonConstant.POKEMON_LIST
import com.lead.networking.data.model.PokemonListRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET(POKEMON_LIST)
    suspend fun listPokemon(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : Response<PokemonListRes>
}