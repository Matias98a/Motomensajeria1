package com.example.intentomotomensajeria1.admin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intentomotomensajeria1.Iniciador
import com.example.intentomotomensajeria1.R
import com.example.intentomotomensajeria1.databinding.FragmentIniciadorAdminBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IniciadorAdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IniciadorAdminFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentIniciadorAdminBinding? = null
    private val binding get() = _binding!!
    private var pasador : Iniciador.Pasador? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pasador = context as Iniciador.Pasador
    }

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
        _binding = FragmentIniciadorAdminBinding.inflate(layoutInflater, container, false)
        val viajesCargados = ViajesCargadosFragment()
        val desigRuta = DesigRutasFragment()
        binding.btnViajesCargados.setOnClickListener { pasador!!.cambiarFragment(viajesCargados) }
        binding.btnDesignarRuta.setOnClickListener { pasador!!.cambiarFragment(desigRuta) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IniciadorAdminFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IniciadorAdminFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}