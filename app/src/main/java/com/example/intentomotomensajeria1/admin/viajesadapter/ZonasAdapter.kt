package com.example.intentomotomensajeria1.admin.viajesadapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.intentomotomensajeria1.Iniciador
import com.example.intentomotomensajeria1.R
import com.example.intentomotomensajeria1.admin.IniciadorAdminFragment

class ZonasAdapter(val listaMoteros : List<Motos>) : RecyclerView.Adapter<ZonasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZonasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ZonasViewHolder(layoutInflater.inflate(R.layout.item_moteros, parent, false))
    }

    override fun onBindViewHolder(holder: ZonasViewHolder, position: Int) {
        val itemZona = listaMoteros[position]
        holder.render(itemZona)

    }

    override fun getItemCount() : Int = listaMoteros.size
}