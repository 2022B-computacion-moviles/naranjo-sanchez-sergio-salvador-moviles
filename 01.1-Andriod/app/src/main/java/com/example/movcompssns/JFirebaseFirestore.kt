package com.example.movcompssns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class JFirebaseFirestore : AppCompatActivity() {
    var query: Query? = null

    val arreglo: ArrayList<ICitiesDto> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jfirebase_firestore)
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }

        val botonFireBaseCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonFireBaseCrear
            .setOnClickListener{
                crearDatosPrueba()
            }

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener{consultarOrderBy(adaptador)}

        val botonObtenerDocumento =findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener{
            consultarDocumento(adaptador)
        }

        val botonFirebaseIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonFirebaseIndiceCompuesto.setOnClickListener{consultarIndiceCompuesto(adaptador)}

        val botonFirebaseEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonFirebaseEliminar.setOnClickListener{
            eliminarRegristro(adaptador)
        }

        val botonFirebaseEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonFirebaseEmpezarPaginar.setOnClickListener{query = null; consultarCiudades(adaptador);}

        val botonFirebasePaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonFirebasePaginar.setOnClickListener{consultarCiudades(adaptador)}

    }
    fun consultarCiudades(
        adaptador: ArrayAdapter<ICitiesDto>
    ){
        val db = Firebase.firestore
        val citiesRef = db.collection("cities")
            .orderBy("population").limit(1)
        var tarea: Task<QuerySnapshot>? = null
        if (query == null){
            tarea = citiesRef.get() //primera vez
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
        } else {
            tarea = query!!.get()
        }

        if (tarea != null){
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, citiesRef)
                    for (ciudad in documentSnapshots){
                        anadirAArregloCiudad(arreglo, ciudad, adaptador)
                    }
                    adaptador.notifyDataSetChanged()
                }
                .addOnFailureListener{
                    //Si hay fallos
                }
        }
    }

    fun eliminarRegristro(
        adaptador: ArrayAdapter<ICitiesDto>
    ){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
            .document("123456789")
            .collection("estudiante")
        referenciaEjemploEstudiante
            .document("123456789")
            .delete()//eliminar
            .addOnCompleteListener{/*Si todo salio bien*/}
            .addOnFailureListener{/*Si algo slio mal*/}
    }


    fun consultarIndiceCompuesto(
        adaptador: ArrayAdapter<ICitiesDto>
    ){
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
        citiesRefUnico
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    anadirAArregloCiudad(arreglo, ciudad, adaptador)
                }
            }
    }


    fun consultarDocumento(
        adaptador: ArrayAdapter<ICitiesDto>
    ){
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
        citiesRefUnico // /cities => population ASCENDING
            .document("BJ")
            .get()
            .addOnSuccessListener {
                it.id //obtener el id del firestore
                limpiarArreglo()
                arreglo.add(
                    ICitiesDto(
                        it.data?.get("name") as String,
                        it.data?.get("state") as String,
                        it.data?.get("country") as String,
                        it.data?.get("capital") as Boolean,
                        it.data?.get("population") as Long,
                        it.data?.get("region") as ArrayList<String>
                    )
                )
                adaptador.notifyDataSetChanged()
            }

    }

    fun consultarOrderBy(
        adaptador: ArrayAdapter<ICitiesDto>
    ){
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()//no usar document por wue document devuelve 1
        citiesRefUnico // /cities => population ASCENDING
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    ciudad.id
                    anadirAArregloCiudad(arreglo,ciudad,adaptador)
                }
            }
    }
    fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    fun anadirAArregloCiudad(
        arregloNuevo: ArrayList<ICitiesDto>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<ICitiesDto>
    ) {
        val nuevaCiudad = ICitiesDto(
            ciudad.data.get("name") as String?, ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?, ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?, ciudad.data.get("regions") as ArrayList<String>
        )
        arregloNuevo.add(
            nuevaCiudad
        )
        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }

    fun crearDatosEjemplo(){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo") // ejemplo/123456789/estudiante/....
            .document("123456789")
            .collection("estudiante")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Adrian",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "Mitad del mundo",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )
    }
}