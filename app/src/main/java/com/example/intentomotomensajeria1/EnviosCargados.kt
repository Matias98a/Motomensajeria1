package com.example.intentomotomensajeria1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intentomotomensajeria1.adapter.DatosEnvios
import com.example.intentomotomensajeria1.adapter.EnviosProgramadosAdapter
import com.example.intentomotomensajeria1.databinding.FragmentEnviosCargadosBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"
private const val ARG_PARAM2 = "empresa"

/**
 * A simple [Fragment] subclass.
 * Use the [EnviosCargados.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnviosCargados : Fragment() {
    private var _binding : FragmentEnviosCargadosBinding? = null
    private val binding get() = _binding!!
    private lateinit var enviosList : ArrayList<DatosEnvios>
    private lateinit var enviosAdapter : EnviosProgramadosAdapter
    private val db = FirebaseFirestore.getInstance()
    // TODO: Rename and change types of parameters
    private var email: String? = null
    private var empresa: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM1)
            empresa = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnviosCargadosBinding.inflate(layoutInflater, container, false)
        initRecyclerView()


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnviosCargados.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String, empresa: String) =
            EnviosCargados().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, email)
                    putString(ARG_PARAM2, empresa)
                }
            }
    }

    fun initRecyclerView(){
        enviosList = ArrayList()
        enviosAdapter = EnviosProgramadosAdapter(enviosList)
        db.collection("Usuarios").document(empresa.toString()).collection("Mis Envios").get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val envioItem = document.toObject(DatosEnvios::class.java)
                    envioItem.zona = document.get("zona").toString()
                    envioItem.emprendimiento = empresa.toString()
                    envioItem.direccion = document.get("direccion").toString()
                    envioItem.entrecalles = document.get("entre calles").toString()
                    envioItem.localidad = document.get("localidad").toString()
                    envioItem.nombre = document.get("nombre").toString()
                    envioItem.numero = document.get("numero").toString()
                    envioItem.horario = document.get("horario").toString()
                    envioItem.precio = document.get("precio producto").toString()
                    binding.recyclerEnvios.layoutManager = LinearLayoutManager(context)
                    binding.recyclerEnvios.adapter = enviosAdapter
                    enviosList.add(envioItem)
                }
            }


    }

}