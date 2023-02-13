package com.example.movcompnssd1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class VerAutosMarca : AppCompatActivity() {
    var idItemSeleccionado = 0
    var idMarca2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_autos_marca)

        //Recivir datos
        val idMarca = intent.extras?.getInt("idMarca")?: "No encontrado"
        idMarca2 = idMarca as Int
        println("Marca a visualizar: ${idMarca}")

        //Buscar los datos de Marca
        var marca = ESqliteHelper(this).consultarMarcaPorId(idMarca as Int)

        //Setear el nombre de la marca
        val textInicio = findViewById<TextView>(R.id.tv_nombre_Marca)
        textInicio.setText(marca.nombre)

        //Traer el array de autos de una marca\
        var arreglo = ESqliteHelper(this).autosMarca(idMarca)
        val listView = findViewById<ListView>(R.id.lv_autos_marca)
        val adaptador  = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        //Menu desplegable
        registerForContextMenu(listView)

        val botonCrear = findViewById<Button>(R.id.btn_crear_auto)
        botonCrear
            .setOnClickListener{
                //redirigir a crear Auto de la marca
                val intent = Intent(this@VerAutosMarca, CrearAutoMarca::class.java)
                intent.putExtra("idMarca", idMarca)
                //Iniciar Editar
                startActivity(intent)
            }
    }

    //Funcion para moverse entre actividades
    fun irActividad(clase: Class<*>){
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
        inflater.inflate(R.menu.menuauto, menu)

        //Obtener el id del ArrayList selecionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        //Crear arreglo
        //println("Datos: ${arreglo}")
        var arreglo = ESqliteHelper(this).autosMarca(idMarca2)
        return when (item.itemId) {
            R.id.editar_auto -> {
                val intent = Intent(this@VerAutosMarca, EditarAuto::class.java)
                intent.putExtra("idMarca", idMarca2)
                intent.putExtra("idAuto",arreglo[idItemSeleccionado].idAuto)
                //Iniciar Editar
                startActivity(intent)
                return true
            }
            R.id.eliminar_auto -> {
                //Eliminar
                //Mostrar mensaje de Confirmacion
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Seguro que desea eliminar")
                builder.setPositiveButton(
                    "Aceptar",
                    DialogInterface.OnClickListener{ dialog, which ->
                        //Eliminar el archivo
                        ESqliteHelper(this).eliminarAutoMarca(arreglo[idItemSeleccionado].idAuto)
                        //Recargar actividad
                        val intent = Intent(this@VerAutosMarca, VerAutosMarca::class.java)
                        intent.putExtra("idMarca", idMarca2)
                        //Iniciar Editar
                        startActivity(intent)
                    }
                )
                builder.setNegativeButton(
                    "Cancelar",
                    null
                )
                val dialogo = builder.create()
                dialogo.show()
                println("Eliminar Auto")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@VerAutosMarca, MainActivity::class.java)
        intent.putExtra("idMarca", idMarca2)
        //Iniciar Editar
        startActivity(intent)
    }
}