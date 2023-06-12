package com.lead.networking.ui.pokemon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lead.networking.R
import com.lead.networking.data.api.PokemonClient
import com.lead.networking.data.model.PokemonListRes
import com.lead.networking.databinding.FragmentPokemonBinding
import com.lead.networking.ui.pokemon.adapter.PokemonAdapter
import com.lead.networking.ui.pokemon.adapter.PokemonItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    val binding get() = _binding!!

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
        loadData()
    }

    private fun setupAdapter() {
        binding.apply {
            rvPokemon.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPokemon.adapter = mPokemonAdapter
        }
    }

    private fun loadData() {
        val client = PokemonClient().buildService()

        // EXAMPLE USING CALL
//        client.listPokemon(
//            limit = 100
//        ).enqueue(
//            object : Callback<PokemonListRes> {
//                override fun onResponse(
//                    call: Call<PokemonListRes>,
//                    response: Response<PokemonListRes>
//                ) {
//                    if (response.isSuccessful) {
////                        Toast.makeText(requireContext(), "Data Loaded with size "+response.body()?.count, Toast.LENGTH_SHORT).show()
//                        val listPokemon = response.body()?.results.orEmpty().map {
//                            PokemonItemModel(
//                                id = it.url.orEmpty(),
//                                title = it.name.orEmpty()
//                            )
//                        }
//                        mPokemonAdapter.submitList(listPokemon)
//                    } else {
//                        Toast.makeText(requireContext(), "Data Load Failed", Toast.LENGTH_SHORT)
//                        Log.e("PokemonFragment", "onResponse: " + response.message())
//                    }
//                }
//
//                override fun onFailure(call: Call<PokemonListRes>, t: Throwable) {
//                    Toast.makeText(requireContext(), "Data Load Failed", Toast.LENGTH_SHORT)
//                    Log.e("PokemonFragment", t.message.orEmpty())
//                }
//
//            }
//        )

//        Example using coroutine scope
        CoroutineScope(Dispatchers.IO).launch {
            val result = client.listPokemon()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    val listPokemon = result.body()?.results.orEmpty().map {
                        PokemonItemModel(
                            id = it.url.orEmpty(),
                            title = it.name.orEmpty()
                        )
                    }
                    mPokemonAdapter.submitList(listPokemon)
                }
            }
        }

//        EXAMPLE using lifecycle scope
//        lifecycleScope.launchWhenCreated {
//            val result = client.listPokemon()
//            if (result.isSuccessful) {
//                val listPokemon = result.body()?.results.orEmpty().map {
//                    PokemonItemModel(
//                        id = it.url.orEmpty(),
//                        title = it.name.orEmpty()
//                    )
//                }
//                mPokemonAdapter.submitList(listPokemon)
//            }
//        }
    }

}