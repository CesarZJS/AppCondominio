package com.example.appcondominio.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appcondominio.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.radiobutton.MaterialRadioButton

class ReservasFragment : Fragment() {

    private lateinit var cardPiscina: MaterialCardView
    private lateinit var cardBBQ: MaterialCardView
    private lateinit var cardSalon: MaterialCardView
    private lateinit var radioGroupHorarios: android.widget.RadioGroup
    private lateinit var btnConfirmar: MaterialButton

    private var selectedArea: String? = null
    private var selectedHorario: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservas, container, false)

        initViews(view)
        setupListeners()

        return view
    }

    private fun initViews(view: View) {
        cardPiscina = view.findViewById(R.id.cardPiscina)
        cardBBQ = view.findViewById(R.id.cardBBQ)
        cardSalon = view.findViewById(R.id.cardSalon)
        btnConfirmar = view.findViewById(R.id.btnConfirmarReserva)
    }

    private fun setupListeners() {
        // Selección de áreas
        cardPiscina.setOnClickListener {
            clearAreaSelection()
            selectedArea = "Piscina"
            updateAreaSelection(cardPiscina)
        }

        cardBBQ.setOnClickListener {
            clearAreaSelection()
            selectedArea = "Zona BBQ"
            updateAreaSelection(cardBBQ)
        }

        cardSalon.setOnClickListener {
            clearAreaSelection()
            selectedArea = "Salón de Eventos"
            updateAreaSelection(cardSalon)
        }

        // Selección de horarios
        radioGroupHorarios.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = view?.findViewById<RadioButton>(checkedId)
            selectedHorario = radioButton?.text.toString()
        }

        // Botón confirmar
        btnConfirmar.setOnClickListener {
            confirmarReserva()
        }
    }

    private fun clearAreaSelection() {
        cardPiscina.isSelected = false
        cardBBQ.isSelected = false
        cardSalon.isSelected = false

        cardPiscina.strokeWidth = 0
        cardBBQ.strokeWidth = 0
        cardSalon.strokeWidth = 0
    }

    private fun updateAreaSelection(selectedCard: MaterialCardView) {
        selectedCard.isSelected = true
        selectedCard.strokeWidth = 4
        selectedCard.strokeColor = resources.getColor(R.color.black, null)
    }

    private fun confirmarReserva() {
        when {
            selectedArea == null -> {
                Toast.makeText(requireContext(), "Por favor selecciona un área", Toast.LENGTH_SHORT).show()
            }
            selectedHorario == null -> {
                Toast.makeText(requireContext(), "Por favor selecciona un horario", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // Aquí guardas la reserva en tu base de datos
                val mensaje = "Reserva confirmada:\nÁrea: $selectedArea\nHorario: $selectedHorario"
                Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()

                // Limpiar selección después de confirmar
                clearAreaSelection()
                radioGroupHorarios.clearCheck()
                selectedArea = null
                selectedHorario = null
            }
        }
    }
}