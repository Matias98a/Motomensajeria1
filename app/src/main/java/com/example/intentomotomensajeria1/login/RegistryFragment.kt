package com.example.intentomotomensajeria1.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.intentomotomensajeria1.Iniciador
import com.example.intentomotomensajeria1.R
import com.example.intentomotomensajeria1.databinding.FragmentRegistryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentRegistryBinding? = null
    private val binding get() = _binding!!
    private var db = FirebaseFirestore.getInstance()
    private var auth = FirebaseAuth.getInstance()
    private var pasador : Iniciador.Pasador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pasador = context as Iniciador.Pasador}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistryBinding.inflate(layoutInflater, container, false)
        binding.btnRegistrarUser.setOnClickListener { createUser() }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun createUser() {
        if (binding.edtEmailRegist.text.isNotEmpty() &&
            binding.edtPassRegist.text.isNotEmpty() &&
            binding.edtEmpresaRegistry.text.isNotEmpty() &&
            binding.edtDireRegistry.text.isNotEmpty() &&
            binding.edtNumRegistry.text.isNotEmpty()
        ) {
            createUserFirestore()
            createUserFirebase()

        } else {
            Toast.makeText(activity, "Complete campos vacios", Toast.LENGTH_SHORT).show()
        }
    }

    fun createUserFirestore(){
        db.collection("Usuarios").document(binding.edtEmpresaRegistry.text.toString()).set(hashMapOf(
            "Emprendimiento" to binding.edtEmpresaRegistry.text.toString(),
            "Email" to binding.edtEmailRegist.text.toString(),
            "Direccion" to binding.edtDireRegistry.text.toString(),
            "Id" to binding.edtNumRegistry.text.toString()))

    }
    fun createUserFirebase(){
        auth.createUserWithEmailAndPassword(binding.edtEmailRegist.text.toString(),
            binding.edtPassRegist.text.toString()).addOnCompleteListener {
            pasador!!.isLoged(binding.edtEmailRegist.text.toString(),
                binding.edtEmpresaRegistry.text.toString()) }
    }
}