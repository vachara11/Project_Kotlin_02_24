package com.vachara.project02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }







            bottomNavigationView = findViewById(R.id.bottom_navigation)

            // ตั้งค่า Fragment เริ่มต้น
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment())
                    .commit()
            }

            // ตั้งค่า Listener สำหรับ Bottom Navigation
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        loadFragment(HomeFragment())
                        true
                    }
                    R.id.navigation_product -> {
                        loadFragment(ProductFragment())
                        true
                    }
                    R.id.navigation_add_product -> {
                        loadFragment(AddProductFragment())
                        true
                    }
                    else -> false
                }
            }
        }

        private fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
}