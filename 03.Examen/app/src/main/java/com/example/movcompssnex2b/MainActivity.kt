package com.example.movcompssnex2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    var idItemSeleccionado = 0
    val FirestoreManager = FirestoreManager()
    var arregloMarcas = ArrayList<Marca>()
    var marcaSelecionada = Marca("","","",0,"")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BotonCrearMarca = findViewById<Button>(R.id.btn_crear_marcas)
        BotonCrearMarca
            .setOnClickListener{
                irActividad(crear_Marca::class.java)
            }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_marca, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id

    }

    var idMarca = ""
    var nombre = ""
    var pais = ""
    var fundacion = 0
    var creador = ""

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.me_editar_marca -> {
                val listView_marca = findViewById<ListView>(R.id.lv_lista_marcass)
                var itemselected = listView_marca.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselected as Marca
                marcaSelecionada = objetoSeleccionado

                idMarca = marcaSelecionada.idMarca
                nombre = marcaSelecionada.nombre.toString()
                pais = marcaSelecionada.pais.toString()
                fundacion = marcaSelecionada.fundacion.toString().toInt()
                creador = marcaSelecionada.creador.toString()

                intentParametrosMarca(editar_Marca::class.java)
                return true
            }
            R.id.me_eliminar_marca -> {
                val listView_marca = findViewById<ListView>(R.id.lv_lista_marcass)
                var itemselected = listView_marca.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselected as Marca
                marcaSelecionada = objetoSeleccionado

                FirestoreManager.eliminarMarca(objetoSeleccionado.idMarca)
                onResume()
                return true
            }
            R.id.me_ver_autos -> {
                val listView_marca = findViewById<ListView>(R.id.lv_lista_marcass)
                var itemselected = listView_marca.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselected as Marca
                marcaSelecionada = objetoSeleccionado

                idMarca = marcaSelecionada.idMarca.toString()
                nombre = marcaSelecionada.nombre.toString()
                intentId(mostrar_Autos::class.java)

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        var marcas = ArrayList<Marca>()
        var marcaEncontrada = Marca("","", "", 0, "")

        db.collection("marca").get()
            .addOnSuccessListener { result ->
                for (document in result){
                    marcaEncontrada.idMarca = document.id
                    marcaEncontrada.nombre = document.data?.get("nombre").toString()
                    marcaEncontrada.pais = document.data?.get("pais").toString()
                    marcaEncontrada.fundacion = document.data?.get("fundacion").toString().toInt()
                    marcaEncontrada.creador = document.data?.get("creador").toString()
                    marcas.add(marcaEncontrada)
                    marcaEncontrada = Marca("","", "", 0, "")
                }
                val listView = findViewById<ListView>(R.id.lv_lista_marcass)
                val adaptador = Adaptador_Marcas(this, marcas)
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
    }




    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

    fun intentParametrosMarca(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        intent.putExtra("nombre",nombre)
        intent.putExtra("pais", pais)
        intent.putExtra("fundacion", fundacion)
        intent.putExtra("creador", creador)

        startActivity(intent)
    }

    fun intentId(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        intent.putExtra("idMarca", idMarca)
        intent.putExtra("nombre", nombre)
        startActivity(intent)
    }
}