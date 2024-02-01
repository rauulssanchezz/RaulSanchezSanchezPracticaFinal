package com.example.practicafinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Inicio : AppCompatActivity() {
    var user:Usuario?=null
    private lateinit var dtb_ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        var auth=FirebaseAuth.getInstance()
        var userAuth=auth.currentUser!!

        dtb_ref= FirebaseDatabase.getInstance().reference
        CoroutineScope(Dispatchers.IO).launch {
            user = Utilidades.obtenerUsuario(dtb_ref)
            Log.d("Usuario", user.toString())
        }

    }
}