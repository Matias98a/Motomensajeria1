package com.example.intentomotomensajeria1.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intentomotomensajeria1.R
import com.example.intentomotomensajeria1.adapter.DatosEnvios
import com.example.intentomotomensajeria1.adapter.EnviosProgramadosAdapter
import com.example.intentomotomensajeria1.admin.viajesadapter.Motos
import com.example.intentomotomensajeria1.admin.viajesadapter.ZonasAdapter
import com.example.intentomotomensajeria1.databinding.FragmentViajesCargadosBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViajesCargadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViajesCargadosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentViajesCargadosBinding? = null
    private val binding get() = _binding!!
    private var db = FirebaseFirestore.getInstance()

    private lateinit var enviosList2 : ArrayList<DatosEnvios>
    private lateinit var enviosAdapter : EnviosProgramadosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViajesCargadosBinding.inflate(layoutInflater, container, false)

        binding.btnZonaOeste.setOnClickListener { initRecyclerZonas("ZONA OESTE") }
        binding.btnZonaNorte.setOnClickListener { initRecyclerZonas("ZONA NORTE") }
        binding.btnZonaSur.setOnClickListener { initRecyclerZonas("ZONA SUR") }
        binding.btnZonaCaba.setOnClickListener { initRecyclerZonas("CABA") }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViajesCargadosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViajesCargadosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    fun initRecyclerZonas(zona : String){
        enviosList2 = ArrayList()
        enviosAdapter = EnviosProgramadosAdapter(enviosList2)
        db.collection("ENVIOS POR ZONA").document("ZONAS").collection(zona).get()
            .addOnSuccessListener { documents->
                for (document in documents){
                    var itemZonas = document.toObject(DatosEnvios::class.java)
                    itemZonas.zona = document.get("zona").toString()
                    itemZonas.emprendimiento = document.get("emprendedor").toString()
                    itemZonas.direccion = document.get("direccion").toString()
                    itemZonas.entrecalles = document.get("entre calles").toString()
                    itemZonas.localidad = document.get("localidad").toString()
                    itemZonas.nombre = document.get("nombre").toString()
                    itemZonas.numero = document.get("numero").toString()
                    itemZonas.horario = document.get("horario").toString()
                    itemZonas.precio = document.get("precio producto").toString()
                    binding.txtTotalViajes.text = documents.size().toString()

                    binding.recyclerZona.layoutManager = LinearLayoutManager(context)
                    binding.recyclerZona.adapter = enviosAdapter
                    enviosList2.add(itemZonas)


                }



            }






    }
}

