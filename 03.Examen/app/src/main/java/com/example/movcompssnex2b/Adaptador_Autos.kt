package com.example.movcompssnex2b

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adaptador_Autos(
    mContext: Context,
    datos: ArrayList<Auto>,
): BaseAdapter() {
    val datosAutos = datos
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
            rowview = inflater.inflate(R.layout.item_auto, parent, false)

            viewHolder.nombre = rowview.findViewById(R.id.row_nombre) as TextView
            rowview.tag = viewHolder
        }else{
            viewHolder = rowview.tag as ViewHolder
        }
        viewHolder.nombre!!.setText(datosAutos.get(position).modelo)

        return rowview
    }

    override fun getCount(): Int {
        return datosAutos.size
    }

    override fun getItem(position: Int): Any {
        return datosAutos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}