package com.example.movcompnssd1

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class BBaseDatosAuto {
    @RequiresApi(Build.VERSION_CODES.O)
    companion object{
        val arregloAuto = arrayListOf<BAuto>()
        init {
            arregloAuto
                .add(
                    BAuto(1, "Sail", 1.4, 17000.50, 1, 2)
                )
            arregloAuto
                .add(
                    BAuto(2, "Cerato", 2.0, 21000.90, 1, 4)
                )
            arregloAuto
                .add(
                    BAuto(3, "6", 2.5, 43999.99, 0, 1)
                )
            arregloAuto
                .add(
                    BAuto(4, "CX-3", 2.7, 24999.99, 0, 1)
                )
            arregloAuto
                .add(
                    BAuto(5, "CLIO E-TECH", 2.0, 19873.78, 0, 5)
                )
        }
    }
}