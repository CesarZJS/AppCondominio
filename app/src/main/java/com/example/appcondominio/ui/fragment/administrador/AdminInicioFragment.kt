package com.example.appcondominio.ui.fragment.administrador

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appcondominio.R
import com.example.appcondominio.databinding.FragmentAdminInicioBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminInicioFragment : Fragment() {

    private var _binding: FragmentAdminInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animarBarras()
        animarNumeros()
    }

//    private fun mostrarFechaActual() {
//        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
//        val fechaActual = dateFormat.format(Date())
//        binding.txtFechaActual.text = fechaActual
//    }

    private fun animarBarras() {
        val barras = listOf(
            binding.barraJul,
            binding.barraAgo,
            binding.barraSep,
            binding.barraOct,
            binding.barraNov,
            binding.barraDic
        )

        // Alturas finales para cada barra
        val alturasFinales = listOf(80, 95, 70, 110, 100, 120)

        barras.forEachIndexed { index, barra ->
            // Guardar altura original
            val alturaOriginal = barra.layoutParams.height

            // Iniciar con altura 0
            barra.layoutParams.height = 0
            barra.requestLayout()

            // Animar después de un delay
            barra.postDelayed({
                val animator = ValueAnimator.ofInt(0, alturasFinales[index])
                animator.duration = 600
                animator.interpolator = BounceInterpolator()
                animator.addUpdateListener { animation ->
                    val altura = animation.animatedValue as Int
                    barra.layoutParams.height = altura
                    barra.requestLayout()
                }
                animator.start()
            }, index * 100L) // Delay progresivo: 0ms, 100ms, 200ms...
        }
    }
    private fun animarNumeros() {
        animarNumero(binding.txtTotalTorres, 0, 5)
        animarNumero(binding.txtTotalDeptos, 0, 130)
        animarNumero(binding.txtTotalPropietarios, 0, 98)
        animarNumero(binding.txtTotalIngresos, 0, 12450)
    }

    private fun animarNumero(textView: TextView, start: Int, end: Int) {
        val animator = ValueAnimator.ofInt(start, end)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            textView.text = animation.animatedValue.toString()
        }
        animator.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}