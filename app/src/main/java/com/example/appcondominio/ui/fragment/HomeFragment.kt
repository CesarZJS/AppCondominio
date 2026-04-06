package com.example.appcondominio.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.appcondominio.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPagar = view.findViewById<Button>(R.id.btn_pagar)
        val btnReservar = view.findViewById<Button>(R.id.btn_reservar)

        btnPagar.setOnClickListener {
            // Navegar a pantalla de pagos
        }

        btnReservar.setOnClickListener {
            // Navegar a pantalla de reservas
        }
    }

}