package com.lead.networking.data.mapper

import com.lead.networking.data.model.PokemonListRes
import com.lead.networking.domain.model.PokemonModel


fun PokemonListRes.PokemonResultRes.toModel() = PokemonModel(
    name.orEmpty(),
    url.orEmpty()
)