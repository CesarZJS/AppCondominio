package com.example.appcondominio

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat

class IniciarRolActivity : AppCompatActivity() {

    private lateinit var layoutPropietario: LinearLayout
    private lateinit var layoutInquilino: LinearLayout
    private lateinit var layoutAdministrador: LinearLayout
    private lateinit var btnContinuar: Button

    private var selectedRole: String? = null

    // Constantes para los roles
    companion object {
        const val EXTRA_USER_ROLE = "user_role"
        const val ROLE_PROPIETARIO = "propietario"
        const val ROLE_INQUILINO = "inquilino"
        const val ROLE_ADMINISTRADOR = "administrador"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        initViews()
        setupListeners()
    }

    private fun initViews() {
        layoutPropietario = findViewById(R.id.layoutPropietario)
        layoutInquilino = findViewById(R.id.layoutInquilino)
        layoutAdministrador = findViewById(R.id.layoutAdministrador)
        btnContinuar = findViewById(R.id.btnContinuar)
    }

    private fun setupListeners() {
        layoutPropietario.setOnClickListener {
            clearSelection()
            selectedRole = ROLE_PROPIETARIO  // Cambiado
            updateSelectionState(layoutPropietario)
        }

        layoutInquilino.setOnClickListener {
            clearSelection()
            selectedRole = ROLE_INQUILINO    // Cambiado
            updateSelectionState(layoutInquilino)
        }

        layoutAdministrador.setOnClickListener {
            clearSelection()
            selectedRole = ROLE_ADMINISTRADOR // Cambiado
            updateSelectionState(layoutAdministrador)
        }

        btnContinuar.setOnClickListener {
            if (selectedRole != null) {
                navigateToMain()
            } else {
                Toast.makeText(this, "Por favor selecciona un rol", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMain() {  // Cambiado el nombre
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_USER_ROLE, selectedRole)  // Enviar el rol
        startActivity(intent)
    //        finish()

    }

    private fun clearSelection() {
        selectedRole = null
        layoutPropietario.isSelected = false
        layoutInquilino.isSelected = false
        layoutAdministrador.isSelected = false
    }

    private fun updateSelectionState(selectedLayout: LinearLayout) {
        layoutPropietario.isSelected = false
        layoutInquilino.isSelected = false
        layoutAdministrador.isSelected = false
        selectedLayout.isSelected = true
    }
}