package com.vachara.project02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Intent


class MainActivity : AppCompatActivity() {
    // ประกาศตัวแปรสำหรับอ้างอิงถึง View ต่างๆ
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            // เชื่อมโยงตัวแปรกับ View ใน layout
            usernameEditText = findViewById(R.id.usernameEditText)
            passwordEditText = findViewById(R.id.passwordEditText)
            loginButton = findViewById(R.id.loginButton)


            // ตั้งค่า onClickListener ให้กับปุ่ม Login
            loginButton.setOnClickListener {
                // เรียกใช้ฟังก์ชันตรวจสอบการ login
                validateLogin()
            }
        }

        private fun validateLogin() {
            // ดึงค่าจาก EditText
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // ตรวจสอบว่า username และ password ตรงกับที่กำหนดหรือไม่
            if (username == "admin" && password == "admin") {


                // แสดง Toast แจ้งว่าล็อกอินสำเร็จ
                Toast.makeText(this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show()

                // ไปยังหน้า AdminActivity
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)

                // ปิดหน้า login ปัจจุบัน
                finish()
            } else {

                // แสดง Toast แจ้งว่าล็อกอินไม่สำเร็จ
                Toast.makeText(this, "เข้าสู่ระบบไม่สำเร็จ", Toast.LENGTH_SHORT).show()
            }
        }
}