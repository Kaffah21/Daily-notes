package com.example.wherehouse

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditProdukActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var editJudul: EditText
    private lateinit var editTanggal: EditText
    private lateinit var editDeskripsi: EditText
    private lateinit var buttonSimpan: Button
    private var produkId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_produk)

        database = FirebaseDatabase.getInstance().getReference("Notes")

        editJudul = findViewById(R.id.editTextJudul)
        editTanggal = findViewById(R.id.editTextTanggal)
        editDeskripsi = findViewById(R.id.editTextDeskripsi)
        buttonSimpan = findViewById(R.id.buttonSimpan)

        // Membatasi input maksimal 8 karakter pada editTanggal
        editTanggal.filters = arrayOf(InputFilter.LengthFilter(8))

        produkId = intent.getStringExtra("produkId")

        produkId?.let {
            database.child(it).get().addOnSuccessListener { snapshot ->
                val produk = snapshot.getValue(Produk::class.java)
                produk?.let {
                    editJudul.setText(produk.judul)
                    editTanggal.setText(produk.tanggal)
                    editDeskripsi.setText(produk.deskripsi)
                }
            }
        }

        buttonSimpan.setOnClickListener {
            val judul = editJudul.text.toString()
            val tanggal = editTanggal.text.toString()
            val deskripsi = editDeskripsi.text.toString()

            if (judul.isBlank() || tanggal.isBlank() || deskripsi.isBlank()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else if (tanggal.length > 8) {
                Toast.makeText(this, "Isi tanggal dengan benar!", Toast.LENGTH_SHORT).show()
            } else {
                val updatedProduk = Produk(produkId, judul, tanggal, deskripsi)
                produkId?.let {
                    database.child(it).setValue(updatedProduk)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Catatan diperbarui", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Gagal memperbarui catatan", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}
