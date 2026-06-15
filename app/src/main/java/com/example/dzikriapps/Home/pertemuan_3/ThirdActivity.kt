package com.example.dzikriapps.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzikriapps.R
import com.example.dzikriapps.databinding.ActivityThirdBinding
import com.example.dzikriapps.utils.NotificationHelper
import com.example.dzikriapps.utils.PermissionHelper
import com.example.dzikriapps.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

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

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        //inisialisasi variabel setiap elemen
//        val btnkirim: Button = findViewById(R.id.btnKirim)
//        val noTujuan: EditText = findViewById(R.id.inputNoTujuan)

        //buat button bisa dilik
        binding.btnKirim.setOnClickListener {
            //menampilkan pesan dalam bentuk Toast dengan menggunakan binding
            val nomor = binding.inputNoTujuan.text
//            Toast.makeText(this, "Pesan berhasil dikirim ke $nomor", Toast.LENGTH_SHORT).show()

            //pindah halaman dari ThirdActivity ke ThirdResultActivity
            val intent = Intent(this, ThirdResultActivity::class.java)
//            startActivity(intent)

//            NotificationHelper.showNotification(
//                this, //Jika panggil di fragment maka requireContext()
//                "Pesanan Anda",
//                "Halo $nomor, Pesanan Anda Sedang Diproses",
//                intent
//            )

            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
            }
            ReminderHelper.setReminder(
                      context = this, //Jika panggil di fragment maka requireContext()
                      hour = calendar.get(Calendar.HOUR_OF_DAY),
                      minute = calendar.get(Calendar.MINUTE),
                      title = "Reminder 1 Menit",
                      message = "Halo $nomor, reminder ini muncul 1 menit setelah tombol ditekan",
                      targetActivity = ThirdResultActivity::class.java
                  )
            Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi...", Toast.LENGTH_SHORT).show()

        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Third"
            subtitle = "Ini adalah activity P3"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}