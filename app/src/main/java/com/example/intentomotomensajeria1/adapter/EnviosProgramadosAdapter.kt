package com.example.intentomotomensajeria1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intentomotomensajeria1.R

class EnviosProgramadosAdapter(val listaEnvios:List<DatosEnvios>) : RecyclerView.Adapter<EnviosProgramadosViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnviosProgramadosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EnviosProgramadosViewHolder(layoutInflater.inflate(R.layout.item_envios, parent, false))
    }

    override fun onBindViewHolder(holder: EnviosProgramadosViewHolder, position: Int) {
        val item = listaEnvios[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listaEnvios.size
}