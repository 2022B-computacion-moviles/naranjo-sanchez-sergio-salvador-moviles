package com.example.movcompnssd1

import com.google.firebase.firestore.FirebaseFirestore

class FireStoreManager {


    private val db = FirebaseFirestore.getInstance()

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
    fun obtenerMarcas(callback: (List<Map<String, Any>>) -> Unit) {
        db.collection("concesionario")
            .get()
            .addOnSuccessListener { result ->
                val concesionarios = mutableListOf<Map<String, Any>>()
                for (concesionario in result) {
                    concesionarios.add(concesionario.data)
                }
                callback(concesionarios)
            }
            .addOnFailureListener { exception ->
                callback(emptyList())
            }
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