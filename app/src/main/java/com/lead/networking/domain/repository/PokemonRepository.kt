package com.lead.networking.domain.repository

import com.lead.networking.domain.model.PokemonModel
import com.lead.networking.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getListPokemon(
        limit: Int = 20,
        offset: Int = 0
    ) : Flow<ApiResult<List<PokemonModel>>>
}