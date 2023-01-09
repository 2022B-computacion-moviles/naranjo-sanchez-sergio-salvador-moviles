package com.example.movcompssns

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    val arreglo:ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver xml
            arreglo
        )
        listView.adapter = adaptador

        adaptador.notifyDataSetChanged()
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)

        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)
            }

        //registrar el view
        registerForContextMenu(listView)
    }//Fin create

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArrayList selecionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_editar->{
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog,
                                              which ->
                //Al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
        val seleccionPrevia = booleanArrayOf(
            true, //lUNES SELECCIONADO
            false, //Martes no selescionado
            false   //Miercoles no selescionado
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            { dialog,
              which,
              isCheked ->
                "Dio click en el item ${which}"
            }
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun anadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador(
                "Ejmeplo", "a@a.com"
            )
        )
        adaptador.notifyDataSetChanged()
    }
}