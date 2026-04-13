package com.example.appcondominio.ui.fragment.administrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcondominio.R
import com.example.appcondominio.data.ConceptoPago
import com.example.appcondominio.databinding.FragmentAdminPagosBinding

class AdminPagosFragment : Fragment() {

    private var _binding: FragmentAdminPagosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ConceptoPagoAdapter
    private var conceptosList = mutableListOf<ConceptoPago>()
    private var editandoId: Int? = null
    private var nextId = 1
    private var propietarioActualId: String = "" // Identificador: "TorreA-101"
    private var propietarioActualNombre: String = ""
    private var propietarioActualTorreDepto: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminPagosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerView()
        setupListeners()
    }

    private fun configurarRecyclerView() {
        adapter = ConceptoPagoAdapter(
            conceptosList,
            onEditClick = { concepto ->
                editarConcepto(concepto)
            },
            onToggleClick = { concepto, nuevoEstado ->
                toggleEstadoConcepto(concepto, nuevoEstado)
            }
        )
        binding.rvConceptos.layoutManager = LinearLayoutManager(context)
        binding.rvConceptos.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnCargarPropietario.setOnClickListener {
            cargarPropietario()
        }

        binding.btnAgregarConcepto.setOnClickListener {
            if (propietarioActualId.isEmpty()) {
                Toast.makeText(context, "Primero carga un propietario", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mostrarFormularioNuevo()
        }

        binding.btnGuardarConcepto.setOnClickListener {
            guardarConcepto()
        }

        binding.btnCancelar.setOnClickListener {
            ocultarFormulario()
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

        propietarioActualId = "Torre$torre-$numeroDepto"
        propietarioActualTorreDepto = "Torre $torre - Depto $numeroDepto"
        propietarioActualNombre = obtenerNombrePropietario(torre, numeroDepto)

        binding.txtNombrePropietario.text = propietarioActualNombre
        binding.txtTorreDepto.text = propietarioActualTorreDepto

        binding.cardPropietario.visibility = View.VISIBLE
        binding.cardListaConceptos.visibility = View.VISIBLE

        // Cargar conceptos de este propietario
        cargarConceptosPorPropietario(propietarioActualId)
    }

    private fun obtenerNombrePropietario(torre: String, depto: String): String {
        // Aquí consultas tu BD o API
        return when ("$torre-$depto") {
            "A-101" -> "Juan Pérez"
            "A-102" -> "María López"
            "B-101" -> "Carlos Rodríguez"
            "B-102" -> "Ana Martínez"
            "C-101" -> "Pedro Sánchez"
            else -> "Propietario"
        }
    }

    private fun cargarConceptosPorPropietario(propietarioId: String) {
        conceptosList.clear()

        // Aquí cargas desde tu BD los conceptos de este propietario
        // Ejemplo con datos simulados:
        when (propietarioId) {
            "TorreA-101" -> {
                conceptosList.addAll(listOf(
                    ConceptoPago(1, "Mantenimiento", "Gastos comunes", 150.00, "Mensual", true, propietarioId),
                    ConceptoPago(2, "Seguridad", "Servicio 24/7", 80.00, "Mensual", true, propietarioId)
                ))
                nextId = 3
            }
            "TorreB-101" -> {
                conceptosList.addAll(listOf(
                    ConceptoPago(3, "Mantenimiento", "Gastos comunes", 150.00, "Mensual", true, propietarioId),
                    ConceptoPago(4, "Limpieza", "Limpieza general", 60.00, "Mensual", false, propietarioId)
                ))
                nextId = 5
            }
            else -> {
                conceptosList.clear()
                nextId = 1
            }
        }

        adapter.updateList(conceptosList)
        actualizarVistaLista()
    }

    private fun mostrarFormularioNuevo() {
        editandoId = null
        binding.txtFormTitulo.text = "Nuevo Concepto"
        limpiarFormulario()
        binding.cardFormConcepto.visibility = View.VISIBLE
        binding.scrollView.post {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun editarConcepto(concepto: ConceptoPago) {
        editandoId = concepto.id
        binding.txtFormTitulo.text = "Editar Concepto"

        binding.etNombreConcepto.setText(concepto.nombre)
        binding.etDescripcion.setText(concepto.descripcion)
        binding.etMonto.setText(concepto.monto.toString())

        val periodicidadPos = when(concepto.periodicidad) {
            "Mensual" -> 0
            "Bimestral" -> 1
            "Trimestral" -> 2
            "Semestral" -> 3
            "Anual" -> 4
            "Único" -> 5
            else -> 0
        }
        binding.spinnerPeriodicidad.setSelection(periodicidadPos)

        if (concepto.activo) {
            binding.chipActivo.isChecked = true
        } else {
            binding.chipInactivo.isChecked = true
        }

        binding.cardFormConcepto.visibility = View.VISIBLE
        binding.scrollView.post {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun guardarConcepto() {
        val nombre = binding.etNombreConcepto.text.toString().trim()
        val descripcion = binding.etDescripcion.text.toString().trim()
        val montoStr = binding.etMonto.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(context, "Ingresa el nombre del concepto", Toast.LENGTH_SHORT).show()
            return
        }

        if (montoStr.isEmpty()) {
            Toast.makeText(context, "Ingresa el monto", Toast.LENGTH_SHORT).show()
            return
        }

        val monto = montoStr.toDoubleOrNull()
        if (monto == null) {
            Toast.makeText(context, "Ingresa un monto válido", Toast.LENGTH_SHORT).show()
            return
        }

        val periodicidad = binding.spinnerPeriodicidad.selectedItem.toString()
        val activo = binding.chipActivo.isChecked

        if (editandoId != null) {
            // Actualizar concepto existente
            val index = conceptosList.indexOfFirst { it.id == editandoId }
            if (index != -1) {
                conceptosList[index] = ConceptoPago(
                    id = editandoId!!,
                    nombre = nombre,
                    descripcion = descripcion,
                    monto = monto,
                    periodicidad = periodicidad,
                    activo = activo,
                    propietarioId = propietarioActualId
                )
                Toast.makeText(context, "Concepto actualizado", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Crear nuevo concepto
            val nuevoConcepto = ConceptoPago(
                id = nextId++,
                nombre = nombre,
                descripcion = descripcion,
                monto = monto,
                periodicidad = periodicidad,
                activo = activo,
                propietarioId = propietarioActualId
            )
            conceptosList.add(nuevoConcepto)
            Toast.makeText(context, "Concepto creado", Toast.LENGTH_SHORT).show()
        }

        adapter.updateList(conceptosList)
        actualizarVistaLista()
        ocultarFormulario()
    }

    private fun toggleEstadoConcepto(concepto: ConceptoPago, nuevoEstado: Boolean) {
        val index = conceptosList.indexOfFirst { it.id == concepto.id }
        if (index != -1) {
            conceptosList[index] = concepto.copy(activo = nuevoEstado)
            adapter.updateList(conceptosList)
            val mensaje = if (nuevoEstado) "Concepto activado" else "Concepto desactivado"
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarFormulario() {
        binding.etNombreConcepto.text?.clear()
        binding.etDescripcion.text?.clear()
        binding.etMonto.text?.clear()
        binding.spinnerPeriodicidad.setSelection(0)
        binding.chipActivo.isChecked = true
    }

    private fun ocultarFormulario() {
        binding.cardFormConcepto.visibility = View.GONE
        limpiarFormulario()
        editandoId = null
    }

    private fun actualizarVistaLista() {
        if (conceptosList.isEmpty()) {
            binding.txtSinConceptos.visibility = View.VISIBLE
            binding.rvConceptos.visibility = View.GONE
        } else {
            binding.txtSinConceptos.visibility = View.GONE
            binding.rvConceptos.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}