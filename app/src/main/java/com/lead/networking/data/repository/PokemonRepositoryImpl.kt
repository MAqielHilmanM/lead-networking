package com.lead.networking.data.repository

import com.lead.networking.data.mapper.toModel
import com.lead.networking.data.remote.PokemonRemoteDataSource
import com.lead.networking.domain.model.PokemonModel
import com.lead.networking.domain.repository.PokemonRepository
import com.lead.networking.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val dataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getListPokemon(
        limit: Int,
        offset: Int
    ): Flow<ApiResult<List<PokemonModel>>> {
        return flow {
            val response = dataSource.getListPokemon(limit, offset)
            if (response.isSuccessful) {
                emit(ApiResult.Success(response.body()?.results.orEmpty().map { it.toModel() }))
            } else {
                val errorMsg = response.errorBody()?.string()
                emit(ApiResult.Error(errorMsg.orEmpty()))
            }
        }.onStart {
            emit(ApiResult.Loading(arrayListOf()))
        }
    }
}