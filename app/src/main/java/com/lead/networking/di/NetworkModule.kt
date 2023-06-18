package com.lead.networking.di

import com.lead.networking.data.remote.PokemonService
import com.lead.networking.data.api.PokemonConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideNetworkClient(
        interceptor: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(PokemonConstant.BASE_URL)
            .client(interceptor)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideNetworkService(
        retrofit: Retrofit
    ): PokemonService = retrofit.create(PokemonService::class.java)
}