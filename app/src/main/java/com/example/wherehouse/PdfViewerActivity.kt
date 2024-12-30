package com.example.wherehouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joanzapata.pdfview.PDFView

class PdfViewerActivity : AppCompatActivity() {
    // Deklarasi variabel
    var pdfview: PDFView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        // Memanggil widget
        pdfview = findViewById(R.id.pdfview)

        // Memastikan pdfview tidak null
        pdfview?.let {
            it.fromAsset("Panduan.pdf")
                .swipeVertical(true)
                .load()
        }
    }
}
