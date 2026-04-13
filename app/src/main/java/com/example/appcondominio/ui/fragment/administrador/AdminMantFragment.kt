package com.example.appcondominio.ui.fragment.administrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcondominio.R
import com.example.appcondominio.data.HorarioDia
import com.example.appcondominio.databinding.FragmentAdminMantBinding
import com.google.android.material.textfield.TextInputEditText


class AdminMantFragment : Fragment() {

    private var _binding: FragmentAdminMantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminMantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCargar.setOnClickListener {
            cargarHorarios()
        }

        binding.btnGuardar.setOnClickListener {
            guardarHorarios()
        }

        binding.btnAgregarMantenimiento.setOnClickListener {
            agregarMantenimiento()
        }
    }

    private fun cargarHorarios() {
        val torrePosicion = binding.spinnerTorre.selectedItemPosition
        val areaPosicion = binding.spinnerAreaComun.selectedItemPosition

        if (torrePosicion == 0 || areaPosicion == 0) {
            Toast.makeText(context, "Selecciona una torre y un área común", Toast.LENGTH_SHORT).show()
            return
        }

        val torre = binding.spinnerTorre.selectedItem.toString()
        val area = binding.spinnerAreaComun.selectedItem.toString()

        binding.txtAreaSeleccionada.text = "$torre - $area"
        binding.cardHorarios.visibility = View.VISIBLE
        binding.cardMantenimiento.visibility = View.VISIBLE
        binding.cardListaMantenimientos.visibility = View.VISIBLE
        binding.txtSinResultados.visibility = View.GONE

        // Aquí cargas los horarios desde tu BD o API
        // binding.etHoraDesde.setText("08:00")
        // binding.etHoraHasta.setText("20:00")
    }

    private fun guardarHorarios() {
        val torre = binding.spinnerTorre.selectedItem.toString()
        val area = binding.spinnerAreaComun.selectedItem.toString()

        val fechaDesde = binding.etFechaDesde.text.toString()
        val fechaHasta = binding.etFechaHasta.text.toString()
        val horaDesde = binding.etHoraDesde.text.toString()
        val horaHasta = binding.etHoraHasta.text.toString()

        var estado = "Disponible"
        when {
            binding.chipMantenimiento.isChecked -> estado = "En Mantenimiento"
        }

        if (fechaDesde.isEmpty() || fechaHasta.isEmpty()) {
            Toast.makeText(context, "Ingresa el rango de fechas", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(context,
            "Horario guardado:\n$torre - $area\n$fechaDesde al $fechaHasta\n$horaDesde a $horaHasta\nEstado: $estado",
            Toast.LENGTH_LONG).show()

    }

    private fun agregarMantenimiento() {
        val torre = binding.spinnerTorre.selectedItem.toString()
        val area = binding.spinnerAreaComun.selectedItem.toString()
        val fecha = binding.etFechaMantenimiento.text.toString()
        val descripcion = binding.etDescripcionMantenimiento.text.toString()

        if (torre == "Seleccionar Torre" || area == "Seleccionar Área Común") {
            Toast.makeText(context, "Primero selecciona torre y área", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(context, "Mantenimiento agregado:\n$area - $fecha\n$descripcion", Toast.LENGTH_LONG).show()

        // Limpiar campos
        binding.etFechaMantenimiento.text?.clear()
        binding.etDescripcionMantenimiento.text?.clear()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}