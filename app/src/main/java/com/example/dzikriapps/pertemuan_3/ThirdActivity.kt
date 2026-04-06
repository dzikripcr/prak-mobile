package com.example.dzikriapps.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikriapps.R
import com.example.dzikriapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //inisialisasi binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //inisialisasi variabel setiap elemen
//        val btnkirim: Button = findViewById(R.id.btnKirim)
//        val noTujuan: EditText = findViewById(R.id.inputNoTujuan)

        //buat button bisa dilik
        binding.btnKirim.setOnClickListener {
            //menampilkan pesan dalam bentuk Toast dengan menggunakan binding
            val nomor = binding.inputNoTujuan.text
            Toast.makeText(this, "Pesan berhasil dikirim ke $nomor", Toast.LENGTH_SHORT).show()

            //pindah halaman dari ThirdActivity ke ThirdResultActivity
            val intent = Intent(this, ThirdResultActivity::class.java)
            startActivity(intent)
        }
    }
}