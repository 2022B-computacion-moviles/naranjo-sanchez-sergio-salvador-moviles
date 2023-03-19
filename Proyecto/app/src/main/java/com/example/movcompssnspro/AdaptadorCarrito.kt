package com.example.movcompssnspro

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCarrito (
    private val contexto: Carrito,
    private val lista: ArrayList<productos>,
    private val recyclerView: RecyclerView
    ):RecyclerView.Adapter<AdaptadorCarrito.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
           val imagen: ImageView = view.findViewById(R.id.imagenCarrito)
           val nombreProducto: TextView = view.findViewById(R.id.textView_nombre_Producto)
           val cantidadProductoText: TextView = view.findViewById(R.id.textView_cantidad_Producto)
           val totalProducto: TextView = view.findViewById(R.id.textView_precioTotal)
           val precioUnitario: TextView = view.findViewById(R.id.textView_PrecioUnitario)
           val botonAumentar: ImageButton = view.findViewById(R.id.imageButton_aumentar)
            val botonReducir: ImageButton = view.findViewById(R.id.imageButton_disminuir)
           val botonEliminar: ImageButton = view.findViewById(R.id.imageButton_eliminar)
            var cantidadProducto = cantidadProductoText.text.toString().toDouble()

        init {

            botonAumentar.setOnClickListener{aumentar()}
            botonReducir.setOnClickListener{reducir()}
            botonEliminar.setOnClickListener{eliminar(nombreProducto.text.toString())}
        }
        fun aumentar(){
            cantidadProducto += 1
            cantidadProductoText.text = cantidadProducto.toString()
            totalProducto.text = (cantidadProducto*precioUnitario.text.toString().toDouble()).toString()
            //DFuncion al contexto
            //Precio total
            contexto.aumentarTotal(cantidadProducto*precioUnitario.text.toString().toDouble())
        }
        fun reducir(){
            cantidadProducto -= 1
            cantidadProductoText.text = cantidadProducto.toString()
            //DFuncion al contexto
            //Precio total
            contexto.reducirTotal(cantidadProducto*precioUnitario.text.toString().toDouble())
        }
        fun eliminar(descripcion: String){
            contexto.eliminar(descripcion)

        }
    }

    //setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            . from(parent.context)
            .inflate(
                R.layout.elemento_carrito,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoActual = this.lista[position]
        holder.nombreProducto.text = productoActual.descripcion
        holder.precioUnitario.text = productoActual.precio.toString()
        holder.cantidadProductoText.text = "1"
        holder.totalProducto.text = productoActual.precio.toString()
    }
    //Tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}