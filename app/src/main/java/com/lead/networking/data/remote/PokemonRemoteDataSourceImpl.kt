package com.lead.networking.data.remote

import com.lead.networking.data.model.PokemonListRes
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(private val pokemonService: PokemonService) : PokemonRemoteDataSource{
    override suspend fun getListPokemon(
        limit: Int,
        offset: Int
    ): Response<PokemonListRes> {
        return pokemonService.listPokemon(limit, offset)
    }
}