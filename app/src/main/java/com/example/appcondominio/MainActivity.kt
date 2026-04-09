package com.example.appcondominio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appcondominio.ui.fragment.administrador.*
import com.example.appcondominio.ui.fragment.inquilino.*
import com.example.appcondominio.ui.fragment.propietario.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var userRole: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRole = intent.getStringExtra(IniciarRolActivity.EXTRA_USER_ROLE)
            ?: IniciarRolActivity.ROLE_INQUILINO

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Cargar el menú según el rol
        setupMenuByRole(bottomNavigation)

        // Configurar listener
        setupBottomNavigation(bottomNavigation)

        // Cargar el fragment inicial
        loadFragment(getFragmentForMenu(R.id.navigation_home))
    }

    private fun setupMenuByRole(bottomNavigation: BottomNavigationView) {
        when (userRole) {
            IniciarRolActivity.ROLE_ADMINISTRADOR -> {
                // Administrador tiene menú con Configuración
                bottomNavigation.menu.clear()
                bottomNavigation.inflateMenu(R.menu.bottom_nav_menu_administrador)
            }
            IniciarRolActivity.ROLE_PROPIETARIO -> {
                // Administrador tiene menú con Configuración
                bottomNavigation.menu.clear()
                bottomNavigation.inflateMenu(R.menu.bottom_nav_menu_propietario)
            }
            else -> {
                // Inquilino y Propietario tienen menú normal
                bottomNavigation.menu.clear()
                bottomNavigation.inflateMenu(R.menu.bottom_nav_menu_inquilino)
            }
        }
    }

    private fun setupBottomNavigation(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(getFragmentForMenu(R.id.navigation_home))
                    true
                }
                R.id.navigation_pagos -> {
                    loadFragment(getFragmentForMenu(R.id.navigation_pagos))
                    true
                }
                R.id.navigation_reservas -> {
                    loadFragment(getFragmentForMenu(R.id.navigation_reservas))
                    true
                }
                R.id.navigation_comunicados -> {
                    loadFragment(getFragmentForMenu(R.id.navigation_comunicados))
                    true
                }
                R.id.navigation_configuracion -> {
                    // Solo administrador tiene este ítem
                    loadFragment(AdminConfiguracionFragment())
                    true
                }
                R.id.navigation_perfil -> {
                    loadFragment(getFragmentForMenu(R.id.navigation_perfil))
                    true
                }
                else -> false
            }
        }

        // Seleccionar el primer item
        bottomNavigation.selectedItemId = R.id.navigation_home
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun getFragmentForMenu(menuId: Int): Fragment {
        return when (userRole) {
            IniciarRolActivity.ROLE_PROPIETARIO -> {
                when (menuId) {
                    R.id.navigation_home -> PropietarioHomeFragment()
//                    R.id.navigation_pagos -> PropietarioPagosFragment()
//                    R.id.navigation_reservas -> PropietarioReservasFragment()
//                    R.id.navigation_comunicados -> PropietarioComunicadosFragment()
//                    R.id.navigation_perfil -> PropietarioPerfilFragment()
                    else -> PropietarioHomeFragment()
                }
            }
            IniciarRolActivity.ROLE_INQUILINO -> {
                when (menuId) {
                    R.id.navigation_home -> InquilinoHomeFragment()
//                    R.id.navigation_pagos -> InquilinoPagosFragment()
//                    R.id.navigation_reservas -> InquilinoReservasFragment()
//                    R.id.navigation_comunicados -> InquilinoComunicadosFragment()
//                    R.id.navigation_perfil -> InquilinoPerfilFragment()
                    else -> InquilinoHomeFragment()
                }
            }
            IniciarRolActivity.ROLE_ADMINISTRADOR -> {
                when (menuId) {
                    R.id.navigation_home -> AdminHomeFragment()
//                    R.id.navigation_pagos -> AdminPagosFragment()
//                    R.id.navigation_reservas -> AdminReservasFragment()
//                    R.id.navigation_comunicados -> AdminComunicadosFragment()
                    R.id.navigation_configuracion -> AdminConfiguracionFragment()
//                    R.id.navigation_perfil -> AdminPerfilFragment()
                    else -> AdminHomeFragment()
                }
            }
            else -> {
                InquilinoHomeFragment()
            }
        }
    }
}