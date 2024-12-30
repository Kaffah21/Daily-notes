package com.example.wherehouse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class ProdukAdapter(
    private val produkList: ArrayList<Produk>
) : RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder>() {

    class ProdukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judul: TextView = itemView.findViewById(R.id.textViewJudul)
        val tanggal: TextView = itemView.findViewById(R.id.textViewTanggal)
        val deskripsi: TextView = itemView.findViewById(R.id.textViewDeskripsi)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEdit)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return ProdukViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = produkList[position]
        holder.judul.text = produk.judul
        holder.tanggal.text = produk.tanggal
        holder.deskripsi.text = produk.deskripsi

        holder.buttonEdit.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditProdukActivity::class.java)
            intent.putExtra("produkId", produk.id)
            context.startActivity(intent)
        }

        holder.buttonDelete.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("Notes")
            produk.id?.let { id ->
                database.child(id).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(holder.itemView.context, "Catatan dihapus", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(holder.itemView.context, "Gagal menghapus catatan", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return produkList.size
    }
}
