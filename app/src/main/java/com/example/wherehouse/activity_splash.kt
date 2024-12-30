package com.example.wherehouse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
class activity_splash : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Memutar suara
        mediaPlayer = MediaPlayer.create(this, R.raw.loading_sound)
        mediaPlayer.start()

        // Delay untuk berpindah ke MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000) // Durasi splash screen
    }

    override fun onDestroy() {
        super.onDestroy()
        // Hentikan suara jika masih bermain
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}