package com.lead.networking.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lead.networking.R
import com.lead.networking.databinding.FragmentPokemonBinding
import com.lead.networking.ui.pokemon.adapter.PokemonAdapter

class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    val binding get() = _binding!!

    private val mProductAdapter : PokemonAdapter by lazy {
        PokemonAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPokemonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupAdapter()
    }

    private fun setupAdapter() {
        binding.apply {
            rvPokemon.layoutManager = LinearLayoutManager(requireContext())
            rvPokemon.adapter = PokemonAdapter()
        }
    }

}