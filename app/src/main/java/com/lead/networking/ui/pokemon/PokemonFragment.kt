package com.lead.networking.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.lead.networking.databinding.FragmentPokemonBinding
import com.lead.networking.domain.model.PokemonModel
import com.lead.networking.ui.pokemon.adapter.PokemonAdapter
import com.lead.networking.ui.pokemon.adapter.PokemonItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    val binding get() = _binding!!

    private val mViewModel : PokemonViewModel by viewModels()
    private val mPokemonAdapter: PokemonAdapter by lazy {
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
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    mViewModel.stateItems.collectLatest {
                        observeItem(it)
                    }
                }
            }
        }
    }

    private fun observeItem(pokemonModels: List<PokemonItemModel>) {
        mPokemonAdapter.submitList(pokemonModels)
    }

    private fun setupAdapter() {
        binding.apply {
            rvPokemon.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPokemon.adapter = mPokemonAdapter
        }
    }
}