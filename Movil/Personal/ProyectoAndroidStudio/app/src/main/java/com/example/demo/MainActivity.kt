package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button

    // Sqlite
    private lateinit var sqLiteHelper: SQLiteHelper

    //Recycler view
    private lateinit var recyclerView: RecyclerView
    private var adapter: PersonaAdapter? = null

    private var persona: PersonaModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init
        initView()

        initRecyClerView()

        sqLiteHelper = SQLiteHelper(this)
        btnAdd.setOnClickListener { addPersona() }
        btnView.setOnClickListener { getPersonas() }
        btnUpdate.setOnClickListener { updatePersona() }

        adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            edName.setText(it.name)
            edEmail.setText(it.email)
            persona = it
        }

        adapter?.setOnClickDeleteItem { deletePersona(it.id) }


    }

    private fun deletePersona(id: Int) {
        if (id == null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estás seguro de quieres eliminar esto ?")
        builder.setCancelable(true)
        builder.setPositiveButton("Sí") { dialog, _ ->
            sqLiteHelper.deletePersonaById(id)
            getPersonas()
            dialog.dismiss()

        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun updatePersona() {
        val name = edName.text.toString()
        val email = edEmail.text.toString()
        if (name == persona?.name && email == persona?.email) {
            Toast.makeText(
                this,
                "No se guardo porque la información es la misma",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (persona == null) return
        val persona = PersonaModel(id = persona!!.id, name = name, email = email)
        val status = sqLiteHelper.updatePersona(persona)
        if (status > -1) {
            clearEditText()
            getPersonas()
        } else {
            Toast.makeText(this, "Actualización falló", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getPersonas() {
        val personaList = sqLiteHelper.getAllStudents()
        Log.e("personaList", "${personaList.size}")

        // Watch RecyclerView data in display
        adapter?.addItems(personaList)

    }

    private fun addPersona() {
        val name = edName.text.toString()
        val email = edEmail.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa los campos requeridos", Toast.LENGTH_SHORT)
                .show()
        } else {
            val persona = PersonaModel(name = name, email = email)
            val status = sqLiteHelper.insertPersona(persona)

            // Check if the insert is success or not
            if (status > -1) {
                Toast.makeText(this, "Persona ingresada", Toast.LENGTH_SHORT).show()
                clearEditText()
                getPersonas()
            } else {
                Toast.makeText(this, "Persona no guardada", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun initRecyClerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonaAdapter()
        recyclerView.adapter = adapter
    }

    private fun clearEditText() {
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edEmail = findViewById(R.id.edEmail)
        btnView = findViewById(R.id.btnView)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)
    }
}