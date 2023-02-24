package com.example.teamsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val chatFragment = ChatFragment()
    val activityFragment = ActivityFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottonNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navegation)

        supportFragmentManager.beginTransaction().replace(R.id.container, chatFragment)

        bottonNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mn_activity -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, activityFragment).commit()
                }

            }
            false
        }
    }
}