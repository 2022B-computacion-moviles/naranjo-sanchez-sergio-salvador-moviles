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
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class mostrar_Autos : AppCompatActivity() {
    var idMarca = ""
    var idItemSelecionado = 0
    val FirestoreManager = FirestoreManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_autos)

        val bundle = intent.extras
        idMarca = bundle?.getString("idMarca").toString()

        val botonCrearAuto = findViewById<Button>(R.id.btn_crear_autos)
        botonCrearAuto
            .setOnClickListener{
                intentCrear(crear_Auto::class.java)
            }

    }

    override fun onResume() {
        super.onResume()

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        var autos = ArrayList<Auto>()
        var autoRecuperado = Auto("","",0.0,0.0,0)

        db.collection("marca").document(idMarca)
            .collection("auto")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    autoRecuperado.idAuto =  document.id
                    autoRecuperado.modelo = document.data?.get("modelo").toString()
                    autoRecuperado.cilindraje = document.data?.get("cilindraje").toString().toDouble()
                    autoRecuperado.precio = document.data?.get("precio").toString().toDouble()
                    autoRecuperado.disponible = document.data?.get("disponible").toString().toInt()
                    autos.add(autoRecuperado)
                    autoRecuperado = Auto("","",0.0,0.0,0)
                }
                val listView = findViewById<ListView>(R.id.lv_lista_autos)
                val adaptador = Adaptador_Autos(this, autos)
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }

        val BotonirMarcas = findViewById<Button>(R.id.btn_ir_marcas)
        BotonirMarcas
            .setOnClickListener{
                irActividad(MainActivity::class.java)
            }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_auto, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSelecionado = id
    }

    var autoSelecionado = Auto("","",0.0, 0.0,0)

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.me_eliminar_auto -> {
                val listView_auto = findViewById<ListView>(R.id.lv_lista_autos)
                var itemselect = listView_auto.getItemAtPosition(idItemSelecionado)
                var objectoSeleecionado = itemselect as Auto
                autoSelecionado = objectoSeleecionado
                FirestoreManager.eliminarAuto(objectoSeleecionado.idAuto, idMarca)
                onResume()

                return true
            }
            R.id.me_editar_auto -> {

                val listView_auto = findViewById<ListView>(R.id.lv_lista_autos)
                var itemselect = listView_auto.getItemAtPosition(idItemSelecionado)
                var objectSelecionado = itemselect as Auto
                autoSelecionado = objectSelecionado
                idAuto = autoSelecionado.idAuto.toString()
                modelo = autoSelecionado.modelo.toString()
                cilindraje = autoSelecionado.cilindraje.toDouble()
                precio = autoSelecionado.precio.toDouble()
                disponible = autoSelecionado.disponible.toInt()
                idmarca = idMarca

                itentparametros(editar_Auto::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    var idAuto = ""
    var modelo = ""
    var cilindraje = 0.0
    var precio = 0.0
    var disponible = 0
    var idmarca = ""

    fun intentCrear(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        intent.putExtra("idMarca", idMarca)
        startActivity(intent)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

    fun itentparametros(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        intent.putExtra("idAuto", idAuto)
        intent.putExtra("modelo", modelo)
        intent.putExtra("cilindraje", cilindraje)
        intent.putExtra("preio", precio)
        intent.putExtra("dsponible", disponible)
        intent.putExtra("idMarca", idmarca)
        startActivity(intent)
    }
}