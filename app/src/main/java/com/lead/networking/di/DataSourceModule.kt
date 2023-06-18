package com.lead.networking.di

import com.lead.networking.data.remote.PokemonService
import com.lead.networking.data.api.PokemonConstant
import com.lead.networking.data.remote.PokemonRemoteDataSource
import com.lead.networking.data.remote.PokemonRemoteDataSourceImpl
import com.lead.networking.data.repository.PokemonRepositoryImpl
import com.lead.networking.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun providePokemonDataSource(
        pokemonService: PokemonService
    ): PokemonRemoteDataSource = PokemonRemoteDataSourceImpl(
        pokemonService
    )
}