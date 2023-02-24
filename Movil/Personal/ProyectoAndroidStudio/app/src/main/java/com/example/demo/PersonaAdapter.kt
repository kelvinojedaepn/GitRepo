package com.example.demo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonaAdapter : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {
    private var personaList: ArrayList<PersonaModel> = ArrayList()
    private var onClickItem: ((PersonaModel) -> Unit)? = null
    private var onClickDeleteItem: ((PersonaModel) -> Unit)? = null


    fun addItems(items: ArrayList<PersonaModel>) {
        this.personaList = items
        Log.e("adapter", "msg")

    }

    fun setOnClickDeleteItem(callback: (PersonaModel)->Unit){
        this.onClickDeleteItem = callback
    }
    fun setOnClickItem(callback: (PersonaModel)->Unit){
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonaViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.items_persona, parent, false)
    )


    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.bindView(persona)
        holder.itemView.setOnClickListener { onClickItem?.invoke(persona)}
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(persona) }
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    class PersonaViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var email = view.findViewById<TextView>(R.id.tvEmail)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)

        fun bindView(persona: PersonaModel) {
            id.text = persona.id.toString()
            name.text = persona.name
            email.text = persona.email

        }


    }
}