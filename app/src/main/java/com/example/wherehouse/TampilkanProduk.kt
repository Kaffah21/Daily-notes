package com.example.wherehouse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import android.widget.Toast



class TampilkanProduk : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var produkList: ArrayList<Produk>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tampilkan_produk)

        database = FirebaseDatabase.getInstance().getReference("Notes")

        recyclerView =findViewById(R.id.recyclerViewProduk)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        produkList = ArrayList()

        bacaData()

    }


    private fun bacaData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                produkList.clear()
                if (snapshot.exists()) {
                    for (produkSnapshot in snapshot.children) {
                        val produk = produkSnapshot.getValue(Produk::class.java)
                        if (produk != null) {
                            produkList.add(produk)
                        }
                    }
                        val adapter = ProdukAdapter(produkList)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@TampilkanProduk, "Tidak ada data ditemukan.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TampilkanProduk, "Gagal mengambil data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}