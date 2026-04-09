package com.example.appcondominio.ui.fragment.administrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appcondominio.R

class AdminConfiguracionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_configuracion, container, false)

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = "Panel de Configuración"

        return view
    }
}