package com.example.intentomotomensajeria1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intentomotomensajeria1.admin.IniciadorAdminFragment
import com.example.intentomotomensajeria1.databinding.ActivityMainBinding
import com.example.intentomotomensajeria1.login.LoginFragment
import com.example.intentomotomensajeria1.login.RegistryFragment
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), Iniciador.Pasador {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding : ActivityMainBinding
    private lateinit var miEmprendimiento : String
    private lateinit var miEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        miEmprendimiento = ""
        miEmail = ""
        val login = LoginFragment()
        launchFragment(login)
        //dbExtraer()

        setContentView(binding.root)
    }

    fun launchFragment(fragment : Fragment){
        val launcher = supportFragmentManager.beginTransaction()
        launcher.replace(binding.contenedorFragments.id, fragment, "main").addToBackStack("main")
        launcher.commit()
    }

    override fun isLoged(email: String, empresa: String) {
        miEmprendimiento = email
        miEmail = empresa
        val iniciador = Iniciador.newInstance(email, empresa)
        launchFragment(iniciador)
    }

    override fun registUser() {
        val registryFragment = RegistryFragment()
        launchFragment(registryFragment)
    }

    override fun pagAgregar(emprendimiento: String) {
        val nuevosViajes = AgregarViajes.newInstance(emprendimiento)
        launchFragment(nuevosViajes)
    }

    override fun enviarDatos(
        emprendedor: String,
        direccion: String,
        entreCalles: String,
        zona: String,
        localidad: String,
        nombre: String,
        numero: String,
        rangohorario: String,
        monto: String
    ) {
        dbGuardarEnvio(emprendedor,direccion,entreCalles,zona,localidad,nombre,numero,rangohorario,monto)
        guardarEnviosMisDatos(emprendedor,direccion,entreCalles,zona,localidad,nombre,numero,rangohorario,monto)
    }


    override fun editarViajes() {
        val editarViajes = EnviosCargados()
        launchFragment(editarViajes)
        //dbExtraer()
    }

    override fun cambiarFragment(fragment: Fragment) {
        launchFragment(fragment)
    }

    override fun iniciarAdmin() {
        val iniciadorAdminFragment = IniciadorAdminFragment()
        launchFragment(iniciadorAdminFragment)
    }

    fun dbGuardarEnvio(emprendedor : String, direccion : String, eCalles : String, zona: String,
                       localidad : String,nombre : String, numero: String, horario: String ,precio : String){
        val mapeoViaje = hashMapOf("emprendedor" to emprendedor,
            "direccion" to direccion,
            "entre calles" to eCalles,
            "zona" to zona,
            "localidad" to localidad,
            "nombre" to nombre,
            "numero" to numero,
            "horario" to horario,
            "precio producto" to precio)

        // guardar en datos para leer desde ADMIN mobile
        db.collection("ENVIOS POR ZONA").document("ZONAS").collection(zona).document(direccion).set(mapeoViaje)

        // guardar para admin leer desde ADMIN pc
        db.collection("ENVIOS ASIGNADOS").document(zona).collection(localidad).document(direccion).set(mapeoViaje)
            .addOnSuccessListener { Toast.makeText(this, " $emprendedor cargaste tu envio con exito !", Toast.LENGTH_LONG).show()
                val iniciador = Iniciador.newInstance(miEmprendimiento, miEmail)
            launchFragment(iniciador)}

    }
        fun guardarEnviosMisDatos(emprendedor : String, direccion : String, eCalles : String, zona: String,
                                  localidad : String,nombre : String, numero: String, horario: String ,precio : String){
            val mapeoMisViajes = hashMapOf("emprendedor" to emprendedor,
                "direccion" to direccion,
                "entre calles" to eCalles,
                "zona" to zona,
                "localidad" to localidad,
                "nombre" to nombre,
                "numero" to numero,
                "horario" to horario,
                "precio producto" to precio)
            db.collection("Usuarios").document(miEmail).collection("Mis Envios").document(direccion).set(mapeoMisViajes)
        }

        /*fun dbExtraer(){
            //db.collection("DATOS ENVIOS").document("@Cowen").collection()
            db.collection("DATOS ENVIOS").get().addOnSuccessListener { resultado ->
                for (documento in resultado){
                    var datosEnvios : DatosEnvios = documento.toObject(DatosEnvios::class.java)
                    Programados.listaEnvios.add(datosEnvios)
                    Log.d("Envio", "${Programados.listaEnvios}")
            }



            }
        }*/


}


