package com.example.practicafinal

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class Utilidades {

    companion object{

        fun crearUsuario(email:String, password:String, nombre:String){
            var dtb_ref= FirebaseDatabase.getInstance().reference
            val usuario=Usuario(nombre, email, password)
            dtb_ref.child("Usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(usuario)
        }

       suspend fun obtenerUsuario(dtb_ref: DatabaseReference):Usuario{
            var usuario:Usuario?=null
           try {
               val dataSnapshot = dtb_ref.child("Usuarios").child(FirebaseAuth.getInstance().currentUser!!.uid).get().await()
               usuario = dataSnapshot.getValue(Usuario::class.java)
           } catch (e: Exception) {
               // Manejar excepción
           }
            return usuario!!
        }

    }
}