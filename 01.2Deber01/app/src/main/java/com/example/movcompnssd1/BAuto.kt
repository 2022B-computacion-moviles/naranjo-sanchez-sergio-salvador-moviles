package com.example.movcompnssd1

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

class BAuto(
    private var idAuto: Int, //Propiedad
    private var modelo: String,
    private var cilindraje: Double,
    private var precio: Double,
    private var disponible: Int,
    private var idMarca: Int
): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ){
    }

    override fun toString(): String {
        return "${modelo}  - ${precio}"
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<BAuto> {
        override fun createFromParcel(parcel: Parcel): BAuto {
            return BAuto(parcel)
        }

        override fun newArray(size: Int): Array<BAuto?> {
            return arrayOfNulls(size)
        }
    }

}