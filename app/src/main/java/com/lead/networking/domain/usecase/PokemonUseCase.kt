package com.lead.networking.domain.usecase

import com.lead.networking.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun getListPokemon(
        limit: Int = 20,
        offset: Int = 0
    ) = pokemonRepository.getListPokemon(limit, offset)
}