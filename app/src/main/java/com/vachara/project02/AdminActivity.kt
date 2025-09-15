package com.vachara.project02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.content.Intent

class AdminActivity : AppCompatActivity() {
    // ประกาศตัวแปรสำหรับอ้างอิงถึงปุ่ม logout
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            // เชื่อมโยงตัวแปรกับ View ใน layout
            logoutButton = findViewById(R.id.logoutButton)

            // ตั้งค่า onClickListener ให้กับปุ่ม Logout
            logoutButton.setOnClickListener {
                // แสดง Toast แจ้งว่ากำลังล็อกเอ้าท์
                Toast.makeText(this, "กำลังออกจากระบบ...", Toast.LENGTH_SHORT).show()

                // กลับไปยังหน้า Login
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // ปิดหน้า admin ปัจจุบัน
                finish()
            }
        }
}