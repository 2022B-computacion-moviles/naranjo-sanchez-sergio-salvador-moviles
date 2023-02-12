package com.example.movcompnssd1

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {
    var idItemSeleccionado = 0
    var idNombre = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Crear lista

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
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menumarca, menu)

        //Obtener el id del ArrayList selecionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id

        //Obtener el nombre del item Selecionado
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        //Crear arreglo
        val arreglo = ESqliteHelper(this).consultarElementos()
        return when (item.itemId) {
            R.id.editar_marca -> {
                println("Estoy aqui en Editar Marca")
                //Crear intent y pasar el indice a otra actividad
                val intent = Intent(this@MainActivity, EditarMarca::class.java)
                intent.putExtra("idMarca", arreglo[idItemSeleccionado].idMarca)
                //Iniciar Editar
                startActivity(intent)
                return true
            }
            R.id.eliminar_marca -> {
                mostrarAlerta()
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
        //Crear arreglo
        val arreglo = ESqliteHelper(this).consultarElementos()
        //Mostrar mensaje de Confirmacion
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seguro que desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{dialog, which ->
                //Eliminar el archivo
                ESqliteHelper(this).eliminarMarcaFormulario(arreglo[idItemSeleccionado].idMarca)
                irActividad(MainActivity::class.java)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}

