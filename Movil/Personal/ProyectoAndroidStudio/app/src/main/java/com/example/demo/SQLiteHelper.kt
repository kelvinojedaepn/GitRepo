package com.example.demo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "persona.db"
        private const val TBL_PERSONA = "tbl_persona"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTblPersona =
            ("CREATE TABLE $TBL_PERSONA($ID INTEGER PRIMARY KEY, $NAME TEXT, $EMAIL TEXT)")
        p0?.execSQL(createTblPersona)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TBL_PERSONA")
        onCreate(p0)
    }

    fun insertPersona(persona: PersonaModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, persona.id)
        contentValues.put(NAME, persona.name)
        contentValues.put(EMAIL, persona.email)
        val success = db.insert(TBL_PERSONA, null, contentValues)
        db.close()
        return success
    }


    fun getAllStudents(): ArrayList<PersonaModel> {
        val personaList: ArrayList<PersonaModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_PERSONA"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val persona = PersonaModel(id = id, name = name, email = email)
                personaList.add(persona)
            } while (cursor.moveToNext())
        }
        return personaList
    }

    fun updatePersona(persona: PersonaModel): Int {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ID, persona.id)
        contentValue.put(NAME, persona.name)
        contentValue.put(EMAIL, persona.email)

        val success = db.update(TBL_PERSONA, contentValue, "id=${persona.id}", null)
        db.close()
        return success


    }

    fun deletePersonaById(id: Int): Int{
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ID, id)
        val success = db.delete(TBL_PERSONA, "id=$id", null)
        db.close()
        return success

    }
}