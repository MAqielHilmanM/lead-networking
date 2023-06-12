package com.lead.networking.pokemon.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.lead.networking.databinding.ItemPokemonBinding

class PokemonViewHolder(val binding: ItemPokemonBinding) : ViewHolder(binding.root) {
    fun bind(
        model: PokemonItemModel
    ) {
        binding.apply {
            tvName.text = model.title
            Glide.with(binding.root.context)
                .load(model.picture)
                .into(ivImage)
        }

    }
}