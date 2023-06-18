package com.lead.networking.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lead.networking.domain.model.PokemonModel
import com.lead.networking.domain.usecase.PokemonUseCase
import com.lead.networking.ui.pokemon.adapter.PokemonItemModel
import com.lead.networking.utils.ApiResult
import com.lead.networking.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {
    private var _stateItems = MutableStateFlow<List<PokemonItemModel>>(arrayListOf())
    val stateItems = _stateItems

    private var _stateLoading = MutableStateFlow(false)
    val stateLoading = _stateLoading

    init {
        loadListPokemon()
    }

    private fun loadListPokemon() {
        viewModelScope.launch {
            pokemonUseCase.getListPokemon().collectLatest {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        _stateLoading.update { false }

                        val datas = it.data.orEmpty()
                        _stateItems.update {
                            datas.map { data ->
                                PokemonItemModel(
                                    data.url,
                                    data.name
                                )
                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        _stateLoading.update { false }
                    }

                    ApiStatus.LOADING -> {
                        _stateLoading.update { true }
                    }
                }
            }
        }
    }
}