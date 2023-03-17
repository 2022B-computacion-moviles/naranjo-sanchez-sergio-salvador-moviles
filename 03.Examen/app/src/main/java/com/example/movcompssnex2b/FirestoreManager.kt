package com.example.movcompssnex2b

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreManager {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var arreglo_Marca = ArrayList<Marca>()

    fun crearMarca(
        nombre: String,
        pais: String,
        fundacion: Int,
        creador: String
    ){
        val dato = hashMapOf(
            "nombre" to nombre,
            "pais" to pais,
            "fundacion" to fundacion,
            "creador" to creador
        )
        db.collection("marca")
            .add(dato)
            .addOnSuccessListener {
                Log.d("Ingreso de Marca", "Exito")
            }
            .addOnFailureListener{
                Log.d("Ingreso de Marca", "Fallo")
            }
    }

    fun editarMarca(
        idMarca: String,
        nombre: String,
        pais: String,
        fundacion: Int,
        creador: String
    ){
        val dato = hashMapOf(
            "nombre" to nombre,
            "pais" to pais,
            "fundacion" to fundacion,
            "creador" to creador
        )
        db.collection("marca").document(idMarca)
            .set(dato)
            .addOnSuccessListener {
                Log.d("Actualizacion de Marca", "Exito")
            }
            .addOnFailureListener{
                Log.d("Actualizacion de Marca", "Fallo")
            }

    }

    fun eliminarMarca(idMarca: String){
        db.collection("marca").document(idMarca)
            .delete()
            .addOnSuccessListener {
                Log.d("Eliminacion de Marca", "Exito")
            }
            .addOnFailureListener {
                Log.d("Eliminacion de Marca", "Fallo")
            }
    }

    fun crearAuto(
        modelo: String,
        cilindraje: Double,
        precio: Double,
        disponible: Int,
        idMarca: String
    ){
        val dato = hashMapOf(
            "modelo" to modelo,
            "cilindraje" to cilindraje,
            "precio" to precio,
            "disponible" to disponible
        )

        db.collection("marca").document(idMarca)
            .collection("auto")
            .add(dato)
            .addOnSuccessListener {
                Log.d("Registro Auto", "Exito")
            }
            .addOnFailureListener {
                Log.d("Registrar Auto", "Fallo")
            }
    }

    fun eliminarAuto(idAuto: String, idMarca: String){
        db.collection("marca").document(idMarca)
            .collection("auto")
            .document(idAuto)
            .delete()
            .addOnSuccessListener {
                Log.d("Eliminacion de Auto", "Exito")
            }
            .addOnFailureListener {
                Log.d("Eliminacion de Auto", "Fallo")
            }
    }

    fun editarAuto(
        idAuto: String,
        modelo: String,
        cilindraje: Double,
        precio: Double,
        disponible: Int,
        idMarca: String
    ){
        val dato = hashMapOf(
            "modelo" to modelo,
            "cilindraje" to cilindraje,
            "precio" to precio,
            "disponible" to disponible
        )
        db.collection("marca").document(idMarca)
            .collection("auto")
            .document(idAuto)
            .set(dato)
            .addOnSuccessListener {
                Log.d("Actualizacion de Auto", "Exito")
            }
            .addOnFailureListener {
                Log.d("Actualizacion de Auto", "Fallo")
            }
    }
}