package com.example.movcompnssd1

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var idItemSeleccionado = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Llamada a la lista marcas
        val arreglo = ESqliteHelper(this).consultarElementos()
        //Menu de cada marca

        val listView = findViewById<ListView>(R.id.lv_list_view_entrenador)
        val adaptador  = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        //Meno desplegable
        registerForContextMenu(listView)

        //llamadas a los botones

    }
    //funcion ir a actividad
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    //Funciones del menu desplegable
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menumarca, menu)
        //Obtener el id del ArrayList selecionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editar_marca -> {
                println("Estoy aqui")
                "${idItemSeleccionado}"
                return true
            }
            R.id.eliminar_marca -> {
                //Mostrar mensaje de Confirmacion
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Eliminar")
                builder.setMessage("Seguro que desea eliminar el elemento")
                builder.setPositiveButton(
                    "Aceptar",
                    DialogInterface.OnClickListener { dialog, which ->
                        //Eliminar el archivo
                        ESqliteHelper(this).eliminarMarcaFormulario(idItemSeleccionado)
                    }
                )
                builder.setNegativeButton(
                    "Cancelar",
                    null
                )

                return true
            }
            R.id.ver_autos -> {
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarAlerta(){
        //Mostrar mensaje de Confirmacion
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seguro que desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{dialog, which ->
                //Eliminar el archivo
                ESqliteHelper(this).eliminarMarcaFormulario(idItemSeleccionado)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
    }
}

