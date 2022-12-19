package com.example.intentomotomensajeria1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intentomotomensajeria1.databinding.FragmentAyudaBinding
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AyudaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AyudaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentAyudaBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentAyudaBinding.inflate(layoutInflater, container, false)

        var url : String = "https://instagram.com/cb650f.brasil?igshid=YmMyMTA2M2Y="
        binding.abrirURL.setOnClickListener {
            var _Link = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, _Link)
            startActivity(intent)

        }
        binding.BtnWsp.setOnClickListener {
            var urlWsp = "https://api.whatsapp.com/send?phone=541165622820&text=Hola!%20%F0%9F%98%8A"
            var uriWsp = Uri.parse(urlWsp)
            val intentWsp = Intent(Intent.ACTION_VIEW, uriWsp)
            startActivity(intentWsp)
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AyudaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AyudaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}