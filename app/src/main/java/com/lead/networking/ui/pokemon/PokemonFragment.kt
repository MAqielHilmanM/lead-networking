package com.lead.networking.ui.pokemon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.lead.networking.data.firebase.PokemonClient
import com.lead.networking.data.model.PokemonResponse
import com.lead.networking.databinding.FragmentPokemonBinding
import com.lead.networking.ui.pokemon.adapter.PokemonAdapter
import com.lead.networking.ui.pokemon.adapter.PokemonItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.EventListener

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
        loadDataRealtime()
//        addPokemon()
    }

    private fun addPokemon() {
        PokemonClient().addPokemon(
            PokemonResponse(
                "Bulbasaurrr",
                "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-lets-go-pikachu-eevee/5/51/Bulbasaur.jpg"
            )
        )
    }

    private fun setupAdapter() {
        binding.apply {
            rvPokemon.layoutManager = LinearLayoutManager(requireContext())
            rvPokemon.adapter = mPokemonAdapter
        }
    }

    fun loadData() {
        PokemonClient().getAllPokemon().addOnSuccessListener {
            val list = arrayListOf<PokemonItemModel>()
            it.documents.forEach {
                val data = it.toObject(PokemonResponse::class.java)
                list.add(
                    PokemonItemModel(
                        it.id.orEmpty(),
                        data?.name.orEmpty(),
                        data?.url.orEmpty()
                    )
                )
            }
            mPokemonAdapter.submitList(list)
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed to get data", Toast.LENGTH_SHORT).show()
            Log.e("Pokemon", "loadData: " + it.message)
        }
    }

    lateinit var listenerCollection: ListenerRegistration
    fun loadDataRealtime() {
        listenerCollection =
            PokemonClient().getAllPokemonCollection().addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("Pokemon", "loadDataRealtime: " + error.message)
                }

                val list = arrayListOf<PokemonItemModel>()
                snapshot?.documents?.forEach {
                    val data = it.toObject(PokemonResponse::class.java)
                    list.add(
                        PokemonItemModel(
                            it.id.orEmpty(),
                            data?.name.orEmpty(),
                            data?.url.orEmpty()
                        )
                    )
                }
                mPokemonAdapter.submitList(list)
            }
    }

    override fun onDestroyView() {
        listenerCollection.remove()

        super.onDestroyView()
    }
}