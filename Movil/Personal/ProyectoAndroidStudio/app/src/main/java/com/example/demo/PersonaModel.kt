package com.example.demo

import kotlin.random.Random

data class PersonaModel(
    val id: Int = getAutoId(),
    var name: String = "",
    var email: String = ""
) {
    companion object {
        fun getAutoId(): Int {
            val random = Random
            return random.nextInt(100)
        }
    }
}


