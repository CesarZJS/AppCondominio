package com.example.appcondominio.ui.fragment.administrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcondominio.R
import com.example.appcondominio.databinding.FragmentAdminValidacionBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class AdminValidacionFragment : Fragment() {

    private var _binding: FragmentAdminValidacionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminValidacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCargar.setOnClickListener {
            cargarPropietario()
        }

        binding.btnGuardar.setOnClickListener {
            guardarAcceso()
        }

        // Mostrar/ocultar selección de áreas según el RadioButton
        binding.rgNivelAcceso.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbParcial -> {
                    binding.txtSeleccionAreas.visibility = View.VISIBLE
                    binding.cardSeleccionAreas.visibility = View.VISIBLE
                }
                else -> {
                    binding.txtSeleccionAreas.visibility = View.GONE
                    binding.cardSeleccionAreas.visibility = View.GONE
                }
            }
        }
    }

    private fun cargarPropietario() {
        val torrePosicion = binding.spinnerTorre.selectedItemPosition
        val numeroDepto = binding.etDepartamento.text.toString()

        if (torrePosicion == 0) {
            Toast.makeText(context, "Selecciona una torre", Toast.LENGTH_SHORT).show()
            return
        }

        if (numeroDepto.isEmpty()) {
            Toast.makeText(context, "Ingresa el número de departamento", Toast.LENGTH_SHORT).show()
            return
        }

        val torre = when(torrePosicion) {
            1 -> "A"
            2 -> "B"
            3 -> "C"
            4 -> "D"
            5 -> "E"
            else -> ""
        }

        binding.txtTorreDepto.text = "Torre $torre - Depto $numeroDepto"
        binding.txtNombrePropietario.text = obtenerNombrePropietario(torre, numeroDepto)

        binding.cardPropietario.visibility = View.VISIBLE
        binding.txtSinResultados.visibility = View.GONE

        // Cargar configuración de acceso existente
        cargarAccesoExistente(torre, numeroDepto)
    }

    private fun obtenerNombrePropietario(torre: String, depto: String): String {
        // Aquí consultas tu BD o API
        return when ("$torre-$depto") {
            "A-101" -> "Juan Pérez"
            "A-102" -> "María López"
            "B-101" -> "Carlos Rodríguez"
            else -> "Propietario"
        }
    }

    private fun cargarAccesoExistente(torre: String, depto: String) {
        // Cargar configuración guardada desde tu BD
        // Por ahora, valores por defecto
        binding.rbTotal.isChecked = true
        limpiarCheckboxes()
    }

    private fun guardarAcceso() {
        val torrePosicion = binding.spinnerTorre.selectedItemPosition
        val numeroDepto = binding.etDepartamento.text.toString()

        if (torrePosicion == 0 || numeroDepto.isEmpty()) {
            Toast.makeText(context, "Selecciona una torre e ingresa un departamento", Toast.LENGTH_SHORT).show()
            return
        }

        val torre = when(torrePosicion) {
            1 -> "A"
            2 -> "B"
            3 -> "C"
            4 -> "D"
            5 -> "E"
            else -> ""
        }

        val nivelAcceso = when (binding.rgNivelAcceso.checkedRadioButtonId) {
            R.id.rbTotal -> "Acceso Total"
            R.id.rbParcial -> "Acceso Parcial"
            R.id.rbNinguno -> "Sin Acceso"
            else -> "Sin Acceso"
        }

        val areasPermitidas = mutableListOf<String>()
        if (binding.chkPiscina.isChecked) areasPermitidas.add("Piscina")
        if (binding.chkGimnasio.isChecked) areasPermitidas.add("Gimnasio")
        if (binding.chkSalonEventos.isChecked) areasPermitidas.add("Salón de Eventos")
        if (binding.chkAreaInfantil.isChecked) areasPermitidas.add("Área Infantil")
        if (binding.chkBBQ.isChecked) areasPermitidas.add("Área BBQ")
        if (binding.chkJardines.isChecked) areasPermitidas.add("Jardines")

        val mensaje = StringBuilder()
        mensaje.append("🔐 Acceso actualizado:\n")
        mensaje.append("Torre $torre - Depto $numeroDepto\n")
        mensaje.append("Nivel: $nivelAcceso\n")
        if (areasPermitidas.isNotEmpty()) {
            mensaje.append("Áreas: ${areasPermitidas.joinToString()}")
        }

        Toast.makeText(context, mensaje.toString(), Toast.LENGTH_LONG).show()

        // Aquí guardas en tu BD o API
    }

    private fun limpiarCheckboxes() {
        binding.chkPiscina.isChecked = false
        binding.chkGimnasio.isChecked = false
        binding.chkSalonEventos.isChecked = false
        binding.chkAreaInfantil.isChecked = false
        binding.chkBBQ.isChecked = false
        binding.chkJardines.isChecked = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}