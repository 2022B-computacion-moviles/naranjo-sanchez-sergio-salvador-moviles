package com.example.movcompnssd1

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

class BAuto(
    public var idAuto: Int, //Propiedad
    public var modelo: String,
    public var cilindraje: Double,
    public var precio: Double,
    public var disponible: Int,
    public var idMarca: Int
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
        return "${modelo} - \$${precio} - ${cilindraje}"
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