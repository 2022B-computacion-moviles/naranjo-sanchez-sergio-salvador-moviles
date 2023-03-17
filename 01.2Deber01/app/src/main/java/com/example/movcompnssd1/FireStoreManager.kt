package com.example.movcompnssd1

import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class FireStoreManager {
    //val FirebaseApp.inicializeApp(this)
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    //Agregar Marca
    fun agregarMarca(datos: Map<String, Any>, callback: (Boolean) -> Unit) {
        db.collection("concesionario")
            .add(datos)
            .addOnSuccessListener { documentReference ->
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }
    //Actualizar Marca
    fun actualizarMarca(id: String, datos: Map<String, Any>, callback: (Boolean) -> Unit) {
        db.collection("concesionario")
            .document(id)
            .update(datos)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }

    //Eliminar Marca
    fun eliminarMarca(id: String, callback: (Boolean) -> Unit) {
        db.collection("concesionario")
            .document(id)
            .delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }
    //Traer la lista de Marcas
    fun obtenerMarcas(): List<BMarca> {
        val db = Firebase.firestore
        val query = db.collection("concesionario")
        val completableDeferred = CompletableDeferred<List<BMarca>>()

        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val marcas = mutableListOf<BMarca>()
                for (marca in task.result!!) {
                    val usuarioEncontrado = BMarca("0", "", "",0,"")
                    val data = marca.data
                    usuarioEncontrado.idMarca = data["id"] as String
                    usuarioEncontrado.nombre = data["nombre"] as String
                    usuarioEncontrado.pais = data["pais"] as String
                    usuarioEncontrado.fundacion = data["fundacion"] as Int
                    usuarioEncontrado.creador = data["creador"] as String
                    marcas.add(usuarioEncontrado)
                }
                completableDeferred.complete(marcas)
            } else {
                completableDeferred.complete(emptyList())
            }
        }

        return runBlocking { completableDeferred.await() }
    }

    //AUTOS
    //Agregar Autos
    fun agregarAuto(concesionarioId: String, datos: Map<String, Any>, callback: (Boolean) -> Unit) {
        db.collection("concesionario")
            .document(concesionarioId)
            .collection("autos")
            .add(datos)
            .addOnSuccessListener { documentReference ->
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }
    //Actualizar Auto
    fun actualizarAuto(concesionarioId: String, autoId: String, datos: Map<String, Any>, callback: (Boolean) -> Unit) {
        db.collection("concesionario")
            .document(concesionarioId)
            .collection("autos")
            .document(autoId)
            .update(datos)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }
    //Eliminar Auto
    fun eliminarAuto(concesionarioId: String, autoId: String, callback: (Boolean) -> Unit) {
        db.collection("concesionario")
            .document(concesionarioId)
            .collection("autos")
            .document(autoId)
            .delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }

    //Traer la lista de autos
    fun obtenerAutos(concesionarioId: String, callback: (List<Map<String, Any>>) -> Unit) {
        db.collection("concesionario")
            .document(concesionarioId)
            .collection("autos")
            .get()
            .addOnSuccessListener { result ->
                val autos = mutableListOf<Map<String, Any>>()
                for (auto in result) {
                    autos.add(auto.data)
                }
                callback(autos)
            }
            .addOnFailureListener { exception ->
                callback(emptyList())
            }
    }
}