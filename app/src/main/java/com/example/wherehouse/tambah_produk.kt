package com.example.wherehouse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class tambah_produk : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_produk)

        database = FirebaseDatabase.getInstance().reference

        // Referensi elemen layout
        val etJudul = findViewById<EditText>(R.id.etJudul)
        val etTanggal = findViewById<EditText>(R.id.etTanggal)
        val etDeskripsi = findViewById<EditText>(R.id.etDeskripsi)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Ketika tombol Simpan ditekan
        btnSimpan.setOnClickListener {
            val judul = etJudul.text.toString()
            val tanggal = etTanggal.text.toString()
            val deskripsi = etDeskripsi.text.toString()

            if (judul.isEmpty() || tanggal.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else if (!tanggal.matches(Regex("\\d{1,8}"))) {
                // Validasi: hanya angka, maksimal 8 karakter
                Toast.makeText(this, "Isi tanggal dengan benar!", Toast.LENGTH_SHORT).show()
            } else {
                tambahProduk(judul, tanggal, deskripsi)
            }
        }

        // Ketika tombol Kembali ditekan
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun tambahProduk(judul: String, tanggal: String, deskripsi: String) {
        // Buat ID unik untuk setiap produk
        val produkId = database.child("Notes").push().key

        if (produkId != null) {
            val produk = mapOf(
                "id" to produkId,
                "judul" to judul,
                "tanggal" to tanggal,
                "deskripsi" to deskripsi
            )

            database.child("Notes").child(produkId).setValue(produk)
                .addOnSuccessListener {
                    Toast.makeText(this, "Catatan berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan data: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
