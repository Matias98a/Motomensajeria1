package com.example.intentomotomensajeria1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.intentomotomensajeria1.databinding.FragmentIniciadorBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_EMAIL = "email"
private const val ARG_EMPRESA = "empresa"
/**
 * A simple [Fragment] subclass.
 * Use the [Iniciador.newInstance] factory method to
 * create an instance of this fragment.
 */
class Iniciador : androidx.fragment.app.Fragment() {

    private var _binding : FragmentIniciadorBinding? = null
    private val binding get() = _binding!!
    private var pasador : Pasador? = null


    // TODO: Rename and change types of parameters
    private var email: String? = null
    private var empresa: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL)
            empresa = it.getString(ARG_EMPRESA)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pasador = context as Pasador
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIniciadorBinding.inflate(layoutInflater, container, false)
        val enviosCargados = EnviosCargados.newInstance(email.toString(), empresa.toString())
        val preciosFragment = PreciosFragment()
        val ayudaFragment = AyudaFragment()
        datosBundle()
        // probando listtext
        var arrayZonas = arrayOf("ZONA NORTE", "ZONA OESTE", "ZONA SUR", "CABA")
        //var adapterArray = ArrayAdapter(requireContext(), )

        //
        binding.btnVerPrecios.setOnClickListener { pasador!!.cambiarFragment(preciosFragment) }
        binding.btnAgregarViaje.setOnClickListener{pasador!!.pagAgregar(binding.txtUserData.text.toString())}
        binding.btnEditarViaje.setOnClickListener { pasador!!.cambiarFragment(enviosCargados) }
        binding.btnAyuda.setOnClickListener { pasador!!.cambiarFragment(ayudaFragment) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Iniciador.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String, empresa: String) =
            Iniciador().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_EMPRESA, empresa)
                }
            }
    }

    fun agregarViajesInter(){
        //pasador!!.pagAgregar()
    }

    fun datosBundle(){
        binding.txtEmailData.text = email
        binding.txtUserData.text = empresa
    }




    interface Pasador{
        fun isLoged(email: String, empresa: String)
        fun registUser()
        fun pagAgregar(emprendimiento : String)
        fun enviarDatos(emprendedor : String ,direccion : String, entreCalles : String, zona : String ,localidad: String,
            nombre : String, numero : String, rangohorario : String, monto : String)
        fun editarViajes()
        fun cambiarFragment(fragment : androidx.fragment.app.Fragment)
        fun iniciarAdmin()
    }
}