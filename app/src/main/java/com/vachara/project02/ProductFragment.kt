package com.vachara.project02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.copy

class ProductFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        recyclerView = view.findViewById(R.id.productsRecyclerView)
        setupRecyclerView()

        return view
    }

    private fun setupRecyclerView() {
        // ตั้งค่า LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // สร้างข้อมูลตัวอย่าง
        productList.addAll(getSampleProducts())

        // ตั้งค่า Adapter
        productAdapter = ProductAdapter(
            products = productList,
            onItemClick = { product -> showProductDetail(product) },
            onEditClick = { product -> editProduct(product) },
            onDeleteClick = { product -> deleteProduct(product) }
        )

        recyclerView.adapter = productAdapter
    }

    private fun getSampleProducts(): List<Product> {
        return listOf(
            Product(1, "ขนมเลย์", 20.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(2, "น้ำโค้ก", 10.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(3, "ลูกอม", 1.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(4, "น้ำส้ม", 10.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(5, "ถั่วลันเตา", 10.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(6, "ถั่วลิสง", 10.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(7, "น้ำเปล่า", 10.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo),
            Product(8, "ถ่าน", 39.00, "จำนวนสินค้า 100 ชิ้น", R.drawable.logo)
        )
    }

    private fun showProductDetail(product: Product) {
        // แสดงรายละเอียดสินค้า
        val message = "ชื่อสินค้า: ${product.name}\nราคา: ${product.price} บาท\nคำอธิบาย: ${product.description}"

        android.app.AlertDialog.Builder(requireContext())
            .setTitle("รายละเอียดสินค้า")
            .setMessage(message)
            .setPositiveButton("ตกลง", null)
            .show()
    }

    private fun editProduct(product: Product) {
        // แสดง Dialog สำหรับแก้ไขสินค้า
        showEditProductDialog(product)
    }

    private fun deleteProduct(product: Product) {
        // แสดง Dialog ยืนยันการลบ
        android.app.AlertDialog.Builder(requireContext())
            .setTitle("ลบสินค้า")
            .setMessage("คุณต้องการลบสินค้า '${product.name}' ใช่หรือไม่?")
            .setPositiveButton("ลบ") { dialog, which ->
                // ลบสินค้าจาก List
                productList.remove(product)
                productAdapter.updateProducts(productList.toList())
                showToast("ลบสินค้า '${product.name}' เรียบร้อยแล้ว")
            }
            .setNegativeButton("ยกเลิก", null)
            .show()
    }

    private fun showEditProductDialog(product: Product) {
        // สร้าง Dialog สำหรับแก้ไขสินค้า
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_product, null)

        // ตั้งค่าข้อมูลปัจจุบันใน Form
        val nameEditText = dialogView.findViewById<android.widget.EditText>(R.id.editProductName)
        val priceEditText = dialogView.findViewById<android.widget.EditText>(R.id.editProductPrice)
        val descEditText = dialogView.findViewById<android.widget.EditText>(R.id.editProductDescription)

        nameEditText.setText(product.name)
        priceEditText.setText(product.price.toString())
        descEditText.setText(product.description)

        val dialog = android.app.AlertDialog.Builder(requireContext())
            .setTitle("แก้ไขสินค้า")
            .setView(dialogView)
            .setPositiveButton("บันทึก") { dialog, which ->
                // ดึงค่าจาก Form
                val newName = nameEditText.text.toString()
                val newPrice = priceEditText.text.toString().toDoubleOrNull() ?: 0.0
                val newDescription = descEditText.text.toString()

                if (newName.isNotEmpty()) {
                    // สร้างสินค้าใหม่
                    val updatedProduct = Product(
                        id = product.id,
                        name = newName,
                        price = newPrice,
                        description = newDescription,
                        imageResId = product.imageResId
                    )

                    // อัพเดทสินค้าใน List
                    val index = productList.indexOfFirst { it.id == product.id }
                    if (index != -1) {
                        productList[index] = updatedProduct
                        productAdapter.updateProducts(productList.toList())
                        showToast("แก้ไขสินค้าเรียบร้อยแล้ว")
                    }
                } else {
                    showToast("กรุณากรอกชื่อสินค้า")
                }
            }
            .setNegativeButton("ยกเลิก", null)
            .create()

        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // ฟังก์ชันสำหรับเพิ่มสินค้าใหม่
    fun addProduct(product: Product) {
        // หา id ที่ไม่ซ้ำ
        val newId = (productList.maxByOrNull { it.id }?.id ?: 0) + 1
        val newProduct = product.copy(id = newId)
        productList.add(newProduct)
        productAdapter.updateProducts(productList.toList())
        showToast("เพิ่มสินค้า '${newProduct.name}' เรียบร้อยแล้ว")
    }
}