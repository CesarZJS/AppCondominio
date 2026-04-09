package com.example.appcondominio.ui.fragment.propietario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appcondominio.R

class PropietarioHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_propietario_home, container, false)

        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Bienvenido Propietario\nGestiona tus propiedades"

        return view
    }
}