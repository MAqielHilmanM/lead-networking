package com.lead.networking.data.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lead.networking.data.firebase.PokemonConstant.COLLECTION_POKEMON
import com.lead.networking.data.model.PokemonResponse

class PokemonClient {
    private val db = Firebase.firestore

    fun getAllPokemon() = db.collection(COLLECTION_POKEMON).get()
    fun getPokemon(documentId: String) =
        db.collection(COLLECTION_POKEMON).document(documentId).get()
    fun getAllPokemonCollection() = db.collection(COLLECTION_POKEMON)

    fun addPokemon(model: PokemonResponse) = db.collection(COLLECTION_POKEMON).add(model)
}