package com.example.practicafinal

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Utilidades {

    companion object{

        fun crearUsuario(email:String, password:String, nombre:String){
            var dtb_ref= FirebaseDatabase.getInstance().reference
            val usuario=Usuario(nombre, email, password)
            dtb_ref.child("Usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(usuario)
        }

    }
}