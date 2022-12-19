package com.example.intentomotomensajeria1.adapter

import com.example.intentomotomensajeria1.admin.viajesadapter.Motos
import com.example.intentomotomensajeria1.admin.viajesadapter.ZonasViewHolder

data class  DatosEnvios (var zona : String ="",
                        var emprendimiento : String ="" ,
                        var direccion : String ="",
                         var entrecalles : String ="",
                         var localidad : String ="",
                         var nombre : String = "",
                         var numero : String = "",
                         var horario : String ="",
                         var precio : String = "", ) {


    companion object {

        val listaEnvios = ArrayList<DatosEnvios>()
        val listaZonas = ArrayList<Motos>()
    }
}