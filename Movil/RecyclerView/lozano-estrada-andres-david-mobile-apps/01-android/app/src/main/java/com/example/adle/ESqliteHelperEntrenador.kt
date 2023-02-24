package com.example.adle

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoGuardar = baseDatosEscritura
            .insert(
                "ENTRENADOR",   // nombre de la tabla
                null,
                valoresAGuardar
            )

        baseDatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",   // Nombre tabla
                "id=?", // Consulta where
                arrayOf(
                    id.toString()
                )   // Parametros
            )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR",   // Nombre tabla
            valoresAActualizar, // Valores a actualizar
            "id=?", // Cláusula Where
                arrayOf(
                    idActualizar.toString()
                )   // Parámetros cláusula where
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador {
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario, // Consulta
            arrayOf(
                id.toString()
            )   // Parámetros consulta
        )

        // Logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        val arreglo = arrayListOf<BEntrenador>()

        if(existeUsuario) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)

                if(id != null) {
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    arreglo.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

}