package com.lead.networking.ui.pokemon.adapter

import androidx.recyclerview.widget.DiffUtil

class PokemonDiffUtil : DiffUtil.ItemCallback<PokemonItemModel>() {
    override fun areItemsTheSame(oldItem: PokemonItemModel, newItem: PokemonItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonItemModel, newItem: PokemonItemModel): Boolean {
        return oldItem == newItem
    }
}