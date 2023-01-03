package com.example.movcompssns

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init{
            arregloBEntrenador
                .add(
                    BEntrenador("Juan", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Pedro", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Andres", "c@c.com")
                )
        }

    }

}