package com.example.appcondominio.ui.fragment.administrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcondominio.R
import com.example.appcondominio.data.ConceptoPago
import com.example.appcondominio.databinding.ItemConceptoPagoBinding

class ConceptoPagoAdapter(
    private var conceptos: List<ConceptoPago>,
    private val onEditClick: (ConceptoPago) -> Unit,
    private val onToggleClick: (ConceptoPago, Boolean) -> Unit
) : RecyclerView.Adapter<ConceptoPagoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemConceptoPagoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(conceptos[position])
    }

    override fun getItemCount(): Int = conceptos.size

    fun updateList(newList: List<ConceptoPago>) {
        conceptos = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemConceptoPagoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(concepto: ConceptoPago) {
            binding.txtNombre.text = concepto.nombre
            binding.txtDescripcion.text = concepto.descripcion
            binding.txtMonto.text = "S/ ${String.format("%.2f", concepto.monto)}"
            binding.txtPeriodicidad.text = concepto.periodicidad

            // Estado
            if (concepto.activo) {
                binding.chipEstado.text = "Activo"
                binding.chipEstado.chipBackgroundColor =
                    android.content.res.ColorStateList.valueOf(0xFF4CAF50.toInt())
            } else {
                binding.chipEstado.text = "Inactivo"
                binding.chipEstado.chipBackgroundColor =
                    android.content.res.ColorStateList.valueOf(0xFF9E9E9E.toInt())
            }

            // Click para editar
            binding.root.setOnClickListener {
                onEditClick(concepto)
            }

            // Click para activar/desactivar
            binding.chipEstado.setOnClickListener {
                onToggleClick(concepto, !concepto.activo)
            }
        }
    }
}