package com.example.appcondominio.ui.fragment.administrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appcondominio.R
import com.example.appcondominio.data.TorresCaracteristicas
import com.example.appcondominio.databinding.FragmentAdminConfiguracionBinding

class AdminConfiguracionFragment : Fragment() {

    private var _binding: FragmentAdminConfiguracionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuscar.setOnClickListener {
            buscarDepartamento()
        }

        binding.etBuscarDepartamento.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                buscarDepartamento()
                true
            } else false
        }

        binding.spinnerTorre.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.etBuscarDepartamento.text?.clear()
                binding.cardDetalles.visibility = View.GONE
                binding.txtSinResultados.visibility = View.GONE

                if (position > 0) { // Ignorar "Seleccionar Torre"
                    mostrarCaracteristicasTorre(position)
                } else {
                    binding.cardCaracteristicasTorre.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        }
    }


    private fun buscarDepartamento() {

        binding.cardCaracteristicasTorre.visibility = View.GONE

        val torreSeleccionada = binding.spinnerTorre.selectedItemPosition
        val numeroDepto = binding.etBuscarDepartamento.text.toString()


        val torre = when(torreSeleccionada) {
            1 -> "A"
            2 -> "B"
            3 -> "C"
            4 -> "D"
            5 -> "E"
            else -> ""
        }

        // Simular búsqueda - Aquí iría tu lógica real (API o BD)
        if (numeroDepto.toIntOrNull() in 101..502) {
            mostrarDetalles(torre, numeroDepto)
        } else {
            binding.cardDetalles.visibility = View.GONE
            binding.txtSinResultados.visibility = View.VISIBLE
            binding.txtSinResultados.text = "❌ No se encontró el departamento $torre - $numeroDepto"
        }
    }

    private fun mostrarDetalles(torre: String, numero: String) {
        binding.txtSinResultados.visibility = View.GONE
        binding.cardDetalles.visibility = View.VISIBLE
        binding.txtTorreDepto.text = "Torre $torre - Depto $numero"

        // Aquí cargas los datos reales desde tu fuente
        // binding.txtPropietarioNombre.text = ...
        // binding.txtInquilinoNombre.text = ...
        // binding.txtPresidenteNombre.text = ...
    }

    private fun mostrarCaracteristicasTorre(posicionTorre: Int) {
        binding.cardDetalles.visibility = View.GONE

        binding.cardCaracteristicasTorre.visibility = View.VISIBLE

        val torreInfo = when(posicionTorre) {
            1 -> TorresCaracteristicas("Torre A", "12 pisos", "24 departamentos", "2 ascensores", "Área social, Salón de eventos", "Seguridad 24/7")
            2 -> TorresCaracteristicas("Torre B", "10 pisos", "20 departamentos", "1 ascensor", "Gimnasio, Terraza", "Seguridad 24/7")
            3 -> TorresCaracteristicas("Torre C", "15 pisos", "30 departamentos", "2 ascensores", "Piscina, Área infantil", "Seguridad 24/7 + CCTV")
            4 -> TorresCaracteristicas("Torre D", "8 pisos", "16 departamentos", "1 ascensor", "Lavandería, Salón de juegos", "Seguridad 24/7")
            5 -> TorresCaracteristicas("Torre E", "20 pisos", "40 departamentos", "3 ascensores", "BBQ, Terraza mirador, Gym", "Seguridad 24/7 + Biometría")
            else -> return
        }

        binding.txtNombreTorre.text = torreInfo.nombre
        binding.txtPisos.text = "● ${torreInfo.pisos}"
        binding.txtDeptos.text = "● ${torreInfo.departamentos}"
        binding.txtAscensores.text = "● ${torreInfo.ascensores}"
        binding.txtAreasComunes.text = "● ${torreInfo.areasComunes}"
        binding.txtSeguridad.text = "● ${torreInfo.seguridad}"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
