package com.example.appcondominio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcondominio.ui.fragment.HomeFragment

class IniciarRolActivity : AppCompatActivity() {

    private lateinit var layoutPropietario: LinearLayout
    private lateinit var layoutInquilino: LinearLayout
    private lateinit var layoutAdministrador: LinearLayout
    private lateinit var btnContinuar: Button

    private var selectedRole: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            selectedRole = "Propietario"
            updateSelectionState(layoutPropietario)
        }

        layoutInquilino.setOnClickListener {
            clearSelection()
            selectedRole = "Inquilino"
            updateSelectionState(layoutInquilino)
        }

        layoutAdministrador.setOnClickListener {
            clearSelection()
            selectedRole = "Administrador"
            updateSelectionState(layoutAdministrador)
        }

        btnContinuar.setOnClickListener {
            if (selectedRole != null) {
                Toast.makeText(this, "Rol seleccionado: $selectedRole", Toast.LENGTH_SHORT).show()
               navigateToInquilino()

            } else {
                Toast.makeText(this, "Por favor selecciona un rol", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToInquilino() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun clearSelection() {
        selectedRole = null

        // Resetear estilos de los layouts
        layoutPropietario.isSelected = false
        layoutInquilino.isSelected = false
        layoutAdministrador.isSelected = false
    }

    private fun updateSelectionState(selectedLayout: LinearLayout) {
        // Resetear todos
        layoutPropietario.isSelected = false
        layoutInquilino.isSelected = false
        layoutAdministrador.isSelected = false

        // Marcar el seleccionado
        selectedLayout.isSelected = true
    }
}