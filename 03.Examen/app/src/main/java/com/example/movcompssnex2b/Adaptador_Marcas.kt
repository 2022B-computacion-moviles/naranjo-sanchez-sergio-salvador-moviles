package com.example.movcompssnex2b

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adaptador_Marcas(
    mContext: Context,
    datos: ArrayList<Marca>,
): BaseAdapter(){

    val datosMarcas = datos
    val ctx = mContext

    inner class ViewHolder(){
        var nombre: TextView?=null
    }

    override fun getView(position: Int, row: View?, parent: ViewGroup?): View? {
        var rowview = row
        var viewHolder: ViewHolder

        if (rowview == null){
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(ctx)
            rowview = inflater.inflate(R.layout.item_marca,parent,false)

            viewHolder.nombre=rowview.findViewById(R.id.row_nombre) as TextView
            rowview.tag = viewHolder
        }else{
            viewHolder = rowview.tag as ViewHolder
        }
        viewHolder.nombre!!.setText(datosMarcas.get(position).nombre)

        return rowview
    }


    override fun getCount(): Int {
        return datosMarcas.size
    }

    override fun getItem(position: Int): Any {
        return datosMarcas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}