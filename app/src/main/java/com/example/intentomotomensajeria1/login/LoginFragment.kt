package com.example.intentomotomensajeria1.login

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.intentomotomensajeria1.Iniciador
import com.example.intentomotomensajeria1.R
import com.example.intentomotomensajeria1.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var auth = FirebaseAuth.getInstance()
    private var esAdmin: String = ""
    private var db = FirebaseFirestore.getInstance()
    private var pasador: Iniciador.Pasador? = null
    private var emailDB: String = ""
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        var emailObtenidoDB: String = "email@gmail.com"
        binding.btnRegister.setOnClickListener { crearUsuario() }
        binding.btnLogin.setOnClickListener { loginUser() }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun crearUsuario() {
        pasador!!.registUser()
    }
    /*fun createUser() {
        if (binding.edtEmailLogin.text.isNotEmpty() && binding.edtPassLogin.text.isNotEmpty()) {

            auth.createUserWithEmailAndPassword(
                binding.edtEmailLogin.text.toString(),
                binding.edtPassLogin.text.toString()).addOnCompleteListener { pasador!!.isLoged() }

        } else {
            Toast.makeText(activity, "Email y/o Contraseña vacios", Toast.LENGTH_SHORT).show()

        }
    }*/

    fun loginUser() {

        var dbb = db.collection("Usuarios").document(binding.edtEmailLogin.text.toString()).get()
        if (binding.edtEmailLogin.text.isNotEmpty() && binding.edtPassLogin.text.isNotEmpty()) {
            dbb.addOnSuccessListener { document ->
                emailDB = document.getString("Email").toString()
                auth.signInWithEmailAndPassword(emailDB, binding.edtPassLogin.text.toString())
                    .addOnSuccessListener {



                        //Consultar si el USUARIO esta en coleccion ADMINISTRADORES
                        var admingConsult = db.collection("Administradores").document("Administrador1").get()
                        admingConsult.addOnSuccessListener { document ->
                            esAdmin = document.get("Admin").toString()

                            if (esAdmin == binding.edtEmailLogin.text.toString()) {
                                pasador!!.iniciarAdmin()
                                Log.d("TAG", "llego? = $esAdmin")

                            } else if (esAdmin != binding.edtEmailLogin.text.toString()) {
                                pasador!!.isLoged(emailDB, binding.edtEmailLogin.text.toString())
                            }

                        }


                    }.addOnFailureListener {
                        Toast.makeText(activity,"Email y/o incorrectos", Toast.LENGTH_SHORT).show() }
            }
        }
    }
}