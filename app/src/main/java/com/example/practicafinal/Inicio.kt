package com.example.practicafinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Inicio : AppCompatActivity() {
    var user:Usuario?=null
    private lateinit var dtb_refer: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

//        var auth=FirebaseAuth.getInstance()
//        var userAuth=auth.currentUser!!
//        var semaforo=false
//
//        dtb_refer= FirebaseDatabase.getInstance().reference
//
//        dtb_refer.child("Usuarios").child(userAuth.uid).get().addOnSuccessListener {
//            user=it.getValue(Usuario::class.java)
//            semaforo=true
//        }
    }
}