package com.example.intentomotomensajeria1.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intentomotomensajeria1.R
import com.example.intentomotomensajeria1.admin.viajesadapter.Motos
import com.example.intentomotomensajeria1.admin.viajesadapter.ZonasAdapter
import com.example.intentomotomensajeria1.databinding.FragmentDesigRutasBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DesigRutasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DesigRutasFragment : Fragment() {
    private lateinit var listaMotos : ArrayList<Motos>
    private lateinit var adapterMotos : ZonasAdapter
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentDesigRutasBinding? = null
    private val binding get() = _binding!!
    private var db = FirebaseFirestore.getInstance()


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
        _binding = FragmentDesigRutasBinding.inflate(layoutInflater, container, false)

        initRecyclerMoteros()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DesigRutasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DesigRutasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

     fun initRecyclerMoteros(){
         listaMotos = ArrayList()
         adapterMotos = ZonasAdapter(listaMotos)
         val motos = db.collection("Moteros").get()
         motos.addOnSuccessListener { documents ->
             for (document in documents){
                 val itemMoto = document.toObject(Motos::class.java)
                 itemMoto.motero = document.id
                 binding.recyclerMoteros.layoutManager = LinearLayoutManager(context)
                 binding.recyclerMoteros.adapter = adapterMotos
                 listaMotos.add(itemMoto)
             }
         }
     }
}