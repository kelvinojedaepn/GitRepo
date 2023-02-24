package com.example.adle

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val contenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        if(result.resultCode == Activity.RESULT_OK) {
            if(result.data != null) {
                val data = result.data
                Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
            }
        }
    }

    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        if(result.resultCode == RESULT_OK) {
            if(result.data != null) {
                if(result.data!!.data != null) {
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(
                        indiceTelefono!!
                    )
                    cursor?.close()
                    Log.i("intent-epn", "Telefono ${telefono}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Base de datos SQLite
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite
            .setOnClickListener {
                irActividad(ECrudEntrenador::class.java)
            }

        val botonRecyclerView = findViewById<Button>(R.id.btn_recycler_view)
        botonRecyclerView
            .setOnClickListener {
                irActividad(GRecycleView::class.java)
            }

        val botonMaps = findViewById<Button>(R.id.btn_google_maps)
        botonMaps
            .setOnClickListener{
                irActividad(HGoogleMapsActivity::class.java)
            }

        val botonFirebaseUIAuth = findViewById<Button>(R.id.btn_intent_firebase_uiauth)
        botonFirebaseUIAuth
            .setOnClickListener {
                irActividad(IFirebaseUIAuth::class.java)
            }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Andrés")
        intentExplicito.putExtra("apellido", "Lozano")
        intentExplicito.putExtra("edad", 24)

        intentExplicito.putExtra(
            "entrenadorPrincipal",
            BEntrenador(1, "Andrés", "Lozano")
        )

        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad (
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}