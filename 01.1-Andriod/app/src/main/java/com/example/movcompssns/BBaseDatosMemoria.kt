package com.example.movcompssns

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init{
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Juan", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Pedro", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Andres", "c@c.com")
                )
        }

    }

}