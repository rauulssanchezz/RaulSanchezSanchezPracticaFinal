package com.example.practicafinal

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class PedidoAdaptador(private val lista:MutableList<Pedido>) : RecyclerView.Adapter<PedidoAdaptador.PedidoViewHolder>(),
    Filterable {

    private lateinit var context: Context
    private var filter_list=lista
    class PedidoViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val nombre=item.findViewById<TextView>(R.id.name_item)
        val precio=item.findViewById<TextView>(R.id.precio_item)
        val estado=item.findViewById<TextView>(R.id.estado)
        val estadoFoto=item.findViewById<ImageView>(R.id.estado_foto)
        val id=item.findViewById<TextView>(R.id.id_pedido)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val item_view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false)
        context = parent.context
        return PedidoViewHolder(item_view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val actual_item=filter_list[position]
        holder.nombre.text="Nombre carta: "+actual_item.nombre
        holder.precio.text="Precio: "+actual_item.precio
        holder.estado.text="Estado: "+actual_item.estado
        holder.id.text=actual_item.id

        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var tipo = sharedPreferences.getString("tipo", "cliente")

        if(tipo.equals("cliente") && actual_item.estado=="pendiente") {
            holder.estadoFoto.setImageResource(R.drawable.pendiente)
        }else if(tipo.equals("admin") && actual_item.estado=="pendiente") {
            holder.estadoFoto.setImageResource(R.drawable.aprobar)
            holder.estadoFoto.setOnClickListener {
                var pedido=Pedido(
                    actual_item.id,
                    actual_item.id_cliente,
                    actual_item.id_producto,
                    "confirmada",
                    actual_item.precio,
                    actual_item.nombre

                )
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("pedidos")
                myRef.child(actual_item.id).setValue(pedido)
                Toast.makeText(context, "Pedido confirmado", Toast.LENGTH_SHORT).show()
            }
        }else{
            holder.estadoFoto.setImageResource(R.drawable.confirmada)
        }
    }

    override fun getItemCount(): Int = filter_list.size

    override fun getFilter(): Filter {
        TODO("Not implemented")
    }
}