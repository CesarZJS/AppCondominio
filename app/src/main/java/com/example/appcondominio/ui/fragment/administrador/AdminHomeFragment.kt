package com.example.appcondominio.ui.fragment.administrador

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appcondominio.R

class AdminHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin_home, container, false)

        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = "Bienvenido Administrador\nPanel de control general"

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), R.color.color_cabecera)))

        }
    }

}