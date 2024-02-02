package com.example.practicafinal.ui.add

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.practicafinal.Carta
import com.example.practicafinal.Utilidades
import com.example.practicafinal.databinding.FragmentAddBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddFragment : Fragment(), CoroutineScope {
    private lateinit var fotoEdit: ImageView
    private lateinit var nombreEdit: TextInputEditText
    private lateinit var categoriaEdit: TextInputEditText
    private lateinit var precioEdit: TextInputEditText
    private lateinit var stockEdit: TextInputEditText
    private lateinit var guardar: Button
    private lateinit var db_ref: DatabaseReference
    private lateinit var lista:MutableList<Carta>
    private var generated_id:String?=""
    private lateinit var job: Job
    private var url_photo: Uri?=null
    private var _binding: FragmentAddBinding? = null
    var aplicattioncontext :Context?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        var this_activity = this
        job = Job()
        fotoEdit = _binding!!.addImage
        nombreEdit = _binding!!.addName
        categoriaEdit = _binding!!.addCategoria
        precioEdit = _binding!!.addPrecio
        stockEdit = _binding!!.addStock
        guardar = _binding!!.guardar
        aplicattioncontext = this.context

        db_ref = FirebaseDatabase.getInstance().reference

        lista = Utilidades.obtenerCartas(db_ref)

        fotoEdit.setOnClickListener {
            galeryAcces.launch("image/*")
        }

        guardar.setOnClickListener {
            if (nombreEdit.text.toString().trim().isEmpty() || categoriaEdit.text.toString().trim()
                    .isEmpty()
                || precioEdit.text.toString().trim().isEmpty() || stockEdit.text.toString().trim()
                    .isEmpty()
            ) {

                if (nombreEdit.text.toString().trim().isEmpty()) {
                    nombreEdit.error = "El nombre no puede estar vacio"
                }
                if (categoriaEdit.text.toString().trim().isEmpty()) {
                    categoriaEdit.error = "La categoria no puede estar vacia"
                }
                if (precioEdit.text.toString().trim().isEmpty()) {
                    precioEdit.error = "El precio no puede estar vacio"
                }
                if (stockEdit.text.toString().trim().isEmpty()) {
                    stockEdit.error = "El stock no puede estar vacio"
                }

            } else if (url_photo == null) {
                Toast.makeText(
                    aplicattioncontext, "Falta seleccionar la foto", Toast.LENGTH_SHORT
                ).show()
            } else if (Utilidades.existeCarta(lista, nombreEdit.text.toString().trim())) {
                Toast.makeText(aplicattioncontext, "Esa carta ya existe", Toast.LENGTH_SHORT)
                    .show()
            } else {
                generated_id = db_ref.child("Cartas").push().key

                this.launch {
                    val url_photo_firebase = Utilidades.guardarFoto(generated_id!!, url_photo!!)

                    var carta = Carta(
                        generated_id!!,
                        nombreEdit.text.toString().trim().capitalize(),
                        precioEdit.text.toString().trim(),
                        "Categoria: " + categoriaEdit.text.toString().trim().capitalize(),
                        stockEdit.text.toString().trim(),
                        url_photo_firebase
                    )
                    Utilidades.crearCarta(db_ref,carta)

                    Utilidades.toastCourutine(this_activity, aplicattioncontext!!, "Carta añadida correctamente")

                }
            }

        }
        return _binding!!.root
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val galeryAcces = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if(it!=null){
            url_photo = it
            fotoEdit.setImageURI(it)
        }
    }
}