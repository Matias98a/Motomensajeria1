package com.example.intentomotomensajeria1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.intentomotomensajeria1.databinding.FragmentAgregarViajesBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_USER = "emprendimiento"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarViajes.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarViajes : Fragment() {
    private var _binding : FragmentAgregarViajesBinding? = null
    private val binding get() = _binding!!
    private var pasador : Iniciador.Pasador? = null
    private var emprendimiento: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            emprendimiento = it.getString(ARG_USER)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pasador = context as Iniciador.Pasador
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgregarViajesBinding.inflate(layoutInflater, container, false)
        datosBundleIniciador()
        // CREACION DE ARRAYS POR LOCALIDADES

        var arrayCaba = arrayOf("AGRONOMIA","ALMAGRO","BALBANERA","BARRACAS","BELGRANO","LA BOCA", "BOEDO", "CABALLITO")
        var arrayNorte = arrayOf("PILAR","BECCAR","SAN ISIDRO","BOULOGNE","TIGRE")
        var arrayOeste = arrayOf("JOSE C PAZ", "MORENO", "CUALTEL V","GRAL RODRIGUEZ","MERLO","MARIANO ACOSTA","MARCOS PAZ", "PADUA", "BELLA VISTA")
        var arraySur = arrayOf("GLEW","LONGCHAPS","FLORENCIO VARELA", "BANFIELD")

        // CREACION DE ADAPTERS DE LOCALIDADES

        var adapterCaba = ArrayAdapter(requireContext(), R.layout.dropdown_item,arrayCaba)
        var adapterNorte = ArrayAdapter(requireContext(), R.layout.dropdown_item,arrayNorte)
        var adapterOeste = ArrayAdapter(requireContext(), R.layout.dropdown_item,arrayOeste)
        var adapterSur = ArrayAdapter(requireContext(), R.layout.dropdown_item,arraySur)

        // SETEANDO ADAPTER DE LOCALIDADES SEGUN ZONA SELECCIONADA

        var arrayZonas = arrayOf("ZONA NORTE", "ZONA OESTE", "ZONA SUR", "CABA")
        var adapterArray = ArrayAdapter(requireContext(), R.layout.dropdown_item, arrayZonas)
        binding.edtZonaId.setAdapter(adapterArray)

        binding.edtZonaId.setOnItemClickListener { parent, _, position, _ ->
            val zonaSelected = parent.getItemAtPosition(position) as String
            when (zonaSelected) {
                "ZONA NORTE" -> binding.autoLocalidad.setAdapter(adapterNorte)
                "ZONA SUR" -> binding.autoLocalidad.setAdapter(adapterSur)
                "ZONA OESTE" -> binding.autoLocalidad.setAdapter(adapterOeste)
                "CABA" -> binding.autoLocalidad.setAdapter(adapterCaba)
            }
        }





        binding.btnAgvAgregarViaje.setOnClickListener { enviardatos2() }

        return binding.root
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param emprendimiento Parameter 1.
         * @return A new instance of fragment AgregarViajes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(emprendimiento: String) =
            AgregarViajes().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER, emprendimiento)
                }
            }
    }
    fun datosBundleIniciador(){
        binding.agvEmprendedor.text = emprendimiento
    }
    // ARREGLAR
    fun enviardatos2(){
        pasador!!.enviarDatos(binding.agvEmprendedor.text.toString(), binding.edtAgvDireccion.text.toString(), binding.edtAgvEntreCalle.text.toString(),
            binding.edtZonaId.text.toString(), binding.autoLocalidad.text.toString(), binding.edtAgvNombre.text.toString(),
            binding.edtAgvNumero.text.toString(), binding.edtAgvHorario.text.toString(), binding.edtAgvCostoProducto.text.toString())

    }


}

