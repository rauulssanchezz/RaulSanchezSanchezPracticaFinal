package com.example.practicafinal.activities.cliente

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practicafinal.Estado_not
import com.example.practicafinal.Pedido
import com.example.practicafinal.R
import com.example.practicafinal.databinding.ActivityInicioClienteBinding
import com.example.practicafinal.ui.administrador.pedidos.PedidosFragmentAdmin
import com.example.practicafinal.ui.cliente.pedidos.PedidosFragmentCliente
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.atomic.AtomicInteger

class InicioCliente : AppCompatActivity() {
    private lateinit var binding: ActivityInicioClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInicioClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navViewCliente

        val navController = findNavController(R.id.nav_host_fragment_activity_navegacion_cliente)

        navView.setupWithNavController(navController)
    }

}