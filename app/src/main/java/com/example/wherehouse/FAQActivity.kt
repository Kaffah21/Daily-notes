package com.example.wherehouse

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FAQActivity : AppCompatActivity() {

    // Deklarasi elemen-elemen UI
    private lateinit var tvQuestion1: TextView
    private lateinit var tvAnswer1: TextView
    private lateinit var tvQuestion2: TextView
    private lateinit var tvAnswer2: TextView
    private lateinit var tvQuestion3: TextView
    private lateinit var tvAnswer3: TextView
    private lateinit var btnBack: Button // Deklarasi tombol kembali

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqactivity)

        // Inisialisasi elemen UI
        tvQuestion1 = findViewById(R.id.tvQuestion1)
        tvAnswer1 = findViewById(R.id.tvAnswer1)
        tvQuestion2 = findViewById(R.id.tvQuestion2)
        tvAnswer2 = findViewById(R.id.tvAnswer2)
        tvQuestion3 = findViewById(R.id.tvQuestion3)
        tvAnswer3 = findViewById(R.id.tvAnswer3)
        btnBack = findViewById(R.id.btnBack) // Inisialisasi tombol kembali

        // Set konten FAQ
        tvQuestion1.text = "Bagaimana cara menambahkan catatan baru?"
        tvAnswer1.text = "Untuk menambahkan catatan, buka menu 'Tambah Catatan' di halaman utama dan isi detailnya."

        tvQuestion2.text = "Bagaimana cara melihat catatan saya?"
        tvAnswer2.text = "Semua catatan yang telah Anda buat dapat ditemukan di menu 'Catatan'."

        tvQuestion3.text = "Bagaimana cara menggunakan panduan aplikasi?"
        tvAnswer3.text = "Anda bisa melihat panduan aplikasi dengan membuka dokumen PDF di menu 'Panduan'."

        // Menambahkan OnClickListener untuk tombol kembali
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
