import java.util.*

// Main.kt
fun main() {
    println("Hello");

    // Tipos de variables:

    // Inmutables (no se puede reasignar):
    val inmutable: String = "Andres";

    // Mutables (se puede reasignar):
    var mutable: String = "Lozano"
    mutable = "Estrada";

    // Sintaxis Duck Typing
    val ejemploVariable = "Ejemplo";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim()

    // Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true

    // Clases JAVA
    val fechaNacimiento: Date = Date()

    // Estructuras condicinales
    if (true) {
    }
    else {
    }

    // Switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"

    val sumaUno = Suma(1, 1)
    Suma.pi
    sumaUno.sumar()
    Suma.historialSumas

    // ARREGLOS

    // Tipos de arreglos

    // Arreglo estático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    // Arreglos dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)

    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    // OPERADORES

    // FOR EACH
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }

    arregloEstatico
        .forEachIndexed {indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    
    // MAP -> Mutar el arreglo
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map {valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map {it + 15}
    // una sola línea porque la lógica es de una sola linea

    println(respuestaMapDos)


    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter {valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 // Expresión condición
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter {it <= 5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> ANY (Alguno cumple?)
    // AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any {valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all {valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // true


    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion5

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // Lógica del negocio
        }

    println(respuestaReduce) // 78
}

// Unit == void
fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}" )
}

fun calcularSueldo (
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional y por defecto
    bonoEspecial: Double? = null, // Opcional o nullable
): Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    }
    else {
        return sueldo * (100 / tasa) + bonoEspecial
    }

}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {
        this.numeroUno = uno
        this.numeroDos = dos

        println("Inicializado")
    }
}

abstract class Numeros( // Constructor primario
//    uno: Int, // Parametro
    protected var numeroUno: Int, // Propiedad de la clase publica
    protected val numeroDos: Int
) {
    init { // Bloque de código del constructor primario
        println("Inicializado")
    }
}

// Extención de una clase abstracta
class Suma(
    uno: Int,
    dos: Int
): Numeros(uno, dos) {
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor(
        uno: Int?,
        dos: Int
    ):this(
        if (uno == null) 0 else uno,
        dos
    )

    public fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object { // Atributos y métodos compartidos entre instancias
        val pi = 3.1415926535
        fun elevarAlCuadraro(num: Int): Int {
            return num * num
        }

        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int) {
            historialSumas.add(valorNuevaSuma)
        }
    }

}