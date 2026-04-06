package com.example.appcondominio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appcondominio.ui.fragment.HomeFragment
import com.example.appcondominio.ui.fragment.PagosFragment
import com.example.appcondominio.ui.fragment.ReservasFragment
import com.example.appcondominio.ui.fragment.ComunicadosFragment
import com.example.appcondominio.ui.fragment.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Cargar el fragment inicial
        loadFragment(HomeFragment())

        // Configurar listener para navegación
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_pagos -> {
                    loadFragment(PagosFragment())
                    true
                }
                R.id.navigation_reservas -> {
                    loadFragment(ReservasFragment())
                    true
                }
                R.id.navigation_comunicados -> {
                    loadFragment(ComunicadosFragment())
                    true
                }
                R.id.navigation_perfil -> {
                    loadFragment(PerfilFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}