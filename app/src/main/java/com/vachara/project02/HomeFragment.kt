package com.vachara.project02

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast



class HomeFragment : Fragment() {
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // ต้องเชื่อมโยงปุ่มหลังจากที่ view ถูกสร้างแล้ว
        logoutButton = view.findViewById(R.id.logoutButton)

        // ตั้งค่า onClickListener
        logoutButton.setOnClickListener {
            // แสดง Toast แจ้งว่ากำลังล็อกเอ้าท์
            Toast.makeText(requireContext(), "กำลังออกจากระบบ...", Toast.LENGTH_SHORT).show()

            // กลับไปยังหน้า Login
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)

            // ปิด Activity ปัจจุบัน (ถ้าเป็น Activity)
            requireActivity().finish()
        }

        return view
    }
}