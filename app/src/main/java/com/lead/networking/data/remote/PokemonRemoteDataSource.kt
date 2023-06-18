package com.lead.networking.data.remote

import com.lead.networking.data.model.PokemonListRes
import retrofit2.Response

interface PokemonRemoteDataSource {
    suspend fun getListPokemon(
        limit: Int,
        offset: Int
    ) : Response<PokemonListRes>
}