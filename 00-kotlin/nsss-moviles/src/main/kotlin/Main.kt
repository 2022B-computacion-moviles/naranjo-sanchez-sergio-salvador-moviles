import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")
    //Tipos de variables
    //Variables inmutables (no resignar) =
    val inmutable: String = "Sergio";
    //inmutable = "Juan"

    //Variables mutables (re asignar)
    var mutable:String = "Sergio";
    mutable = "Juan";

    //val > vars

    //Duck Typing
    var ejemploVariable = "Ejemplo"
    ejemploVariable.trim()
    val edadEjemplo = 12  //declarar varibles sin necesidad de escribir el tipo

    //Variable primitivas
    val nombreProfesor:String = "Adrian Eguez"
    val sueldo:Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad = true

    //Clases
    val fechaNacimiento: Date = Date()    //no se usa "new" en clases

    //if
    if(true){

    }else{

    }

    //swtch no existe
    val estadoCivilWhen = 'S'
    when(estadoCivilWhen){
        ('S') ->{
            println("Soltero")
        }
        'C'-> println("Casado")
        else -> println("Desconocido")
    }
    val coqueto = if(estadoCivilWhen == 'S') println("Si") else println("No")


    val sumaUno = Suma(1,2)
    val sumaDos = Suma(1,null)
    val sumaTres = Suma(null,2)
    val sumaCuatro = Suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.historiaSumas)

}

fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.00, //opcional por defecto
    bonoEspecial: Double? = null //opcional nulo  //? indica que la variable puede ser nulo en algun momento
):Double{
    if(bonoEspecial != null){
        return sueldo * tasa * bonoEspecial
    }
    return sueldo * tasa
}


//Clases
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int, //Paramwtro
        dos: Int  //Parametro
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Iniciando")
    }
}

abstract class Numeros(//Constructor Primario
    //uno: Int, //Parametro
    //public var uno: Int //Propiedad
    protected val numeroUno: Int, //Propiedad
    protected val numeroDos: Int //Propiedad
    ){
    init {//Bloque de codigo contructor primario
        //this.numeroUno = uno
        //this.numeroDos = dos
        numeroUno
        numeroDos
        println("Iniciando")
    }
}

class Suma(//Costructor Primario Suma
    uno: Int, // Parametro
    dos: Int, // Parametro
): Numeros( //Heredamos de la clase numero
  //super constructoe Numeros
    uno,
    dos,
){
    init {//Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor(//segunndo constructor
        uno: Int?, //Parametro
        dos: Int   //Parametros
    ):this(
        if (uno == null) 0 else uno,
        dos
    ){}

    constructor(//tercer constructor
        uno: Int, //Parametro
        dos: Int?   //Parametros
    ):this(
        uno,
        if (dos == null) 0 else dos,
    ){}

    constructor(//cuarto constructor
        uno: Int?, //Parametro
        dos: Int?,   //Parametros
    ):this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    ){}

    fun sumar():Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object{
        val pi = 3.14 // Suma.pi -> 3.14
        val historiaSumas = arrayListOf<Int>() //Suma.historialSumas
        fun agregarHistorial(valorNuevaSuma: Int){
            historiaSumas.add(valorNuevaSuma)
        }
    }
}























