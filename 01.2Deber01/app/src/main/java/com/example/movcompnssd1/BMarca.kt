package com.example.movcompnssd1

import android.os.Parcel
import android.os.Parcelable

open class BMarca(
    public var idMarca: String, //Propiedad
    public var nombre: String,
    public var pais: String,
    public var fundacion: Int,
    public var creador: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "${nombre} - ${pais} - ${fundacion} - ${creador}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMarca)
        parcel.writeString(nombre)
        parcel.writeString(pais)
        parcel.writeInt(fundacion)
        parcel.writeString(creador)
    }

    companion object CREATOR : Parcelable.Creator<BMarca> {
        override fun createFromParcel(parcel: Parcel): BMarca {
            return BMarca(parcel)
        }

        override fun newArray(size: Int): Array<BMarca?> {
            return arrayOfNulls(size)
        }
    }
}