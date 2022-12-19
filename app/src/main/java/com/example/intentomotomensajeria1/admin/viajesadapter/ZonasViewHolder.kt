package com.example.intentomotomensajeria1.admin.viajesadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.intentomotomensajeria1.databinding.ItemMoterosBinding
import com.example.intentomotomensajeria1.databinding.ItemZonasBinding

class ZonasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemMoterosBinding.bind(view)

    fun render(listaMoteros : Motos){
        binding.txtNombreMotero.text = listaMoteros.motero.toString()
    }
}