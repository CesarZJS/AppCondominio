package com.example.appcondominio.ui.fragment.inquilino

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appcondominio.R

class InquilinoHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inquilino_home, container, false)

        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Bienvenido Inquilino\nGestiona tu unidad"

        return view
    }
}