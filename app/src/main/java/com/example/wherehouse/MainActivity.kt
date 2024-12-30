package com.example.wherehouse

import android.os.Bundle
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wherehouse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cv1.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Bantuan?")
                .setIcon(R.drawable.logo)
                .setMessage("Perlu bantuan tidak?")
                .setPositiveButton("YA") { dialogInterface, i ->
                    val wpurl =
                        "https://wa.me/+6281220381892?text= Saya butuh bantuan nih"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(wpurl)
                    startActivity(intent)
                }
                .setNegativeButton("Tidak") { dialogInterface, i ->
                    dialogInterface.cancel()
                }
                .show()
        }



        binding.cv2.setOnClickListener{
            val intent = Intent(this@MainActivity,
                PdfViewerActivity::class.java)
            startActivity(intent)        }

        binding.cv3.setOnClickListener{
            val intent = Intent(this@MainActivity, FAQActivity::class.java)
            startActivity(intent)        }

        binding.cv4.setOnClickListener{
val intent = Intent(this@MainActivity,TampilkanProduk::class.java)
            startActivity(intent)
        }

        // Tambah Produk
        binding.cv5.setOnClickListener {
            val intent = Intent(this, tambah_produk::class.java)
            startActivity(intent)
        }

        // Keluar Aplikasi
        binding.cv6.setOnClickListener {
            keluarAplikasi()
        }
    }

    private fun keluarAplikasi() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Keluar")
        builder.setMessage("Apakah Anda yakin ingin keluar?")
        builder.setPositiveButton("Ya") { _: DialogInterface, _: Int ->
            finishAffinity() // Keluar dari aplikasi
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}
