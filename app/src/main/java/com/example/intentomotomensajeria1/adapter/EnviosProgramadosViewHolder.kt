package com.example.intentomotomensajeria1.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.intentomotomensajeria1.databinding.ItemEnviosBinding

class EnviosProgramadosViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEnviosBinding.bind(view)

    fun render(enviosCargados : DatosEnvios){
        binding.itemEmprendedor.text = enviosCargados.emprendimiento
        binding.itemZona.text = enviosCargados.zona
        binding.itemDireccion.text = enviosCargados.direccion
        binding.itemECalles.text = enviosCargados.entrecalles
        binding.itemLocalidad.text = enviosCargados.localidad
        binding.itemNombre.text = enviosCargados.nombre
        binding.itemNumero.text = enviosCargados.numero
        binding.itemPrecio.text = enviosCargados.precio.toString()
        binding.itemHorario.text = enviosCargados.horario
    }

}