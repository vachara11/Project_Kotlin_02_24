package com.vachara.project02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddProductFragment : Fragment() {

    private lateinit var editProductName: EditText
    private lateinit var editProductPrice: EditText
    private lateinit var editProductDescription: EditText
    private lateinit var btnAddProduct: Button
    private lateinit var btnClearForm: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        // Initialize views
        editProductName = view.findViewById(R.id.editProductName)
        editProductPrice = view.findViewById(R.id.editProductPrice)
        editProductDescription = view.findViewById(R.id.editProductDescription)
        btnAddProduct = view.findViewById(R.id.btnAddProduct)
        btnClearForm = view.findViewById(R.id.btnClearForm)

        // Set up click listeners
        setupClickListeners()

        return view
    }

    private fun setupClickListeners() {
        btnAddProduct.setOnClickListener {
            addProduct()
        }

        btnClearForm.setOnClickListener {
            clearForm()
        }
    }

    private fun addProduct() {
        val name = editProductName.text.toString().trim()
        val priceText = editProductPrice.text.toString().trim()
        val description = editProductDescription.text.toString().trim()

        // Validate input
        if (name.isEmpty()) {
            editProductName.error = "กรุณากรอกชื่อสินค้า"
            editProductName.requestFocus()
            return
        }

        if (priceText.isEmpty()) {
            editProductPrice.error = "กรุณากรอกราคา"
            editProductPrice.requestFocus()
            return
        }

        val price = try {
            priceText.toDouble()
        } catch (e: NumberFormatException) {
            editProductPrice.error = "กรุณากรอกราคาเป็นตัวเลข"
            editProductPrice.requestFocus()
            return
        }

        if (price <= 0) {
            editProductPrice.error = "ราคาต้องมากกว่า 0"
            editProductPrice.requestFocus()
            return
        }

        // Create new product
        val newProduct = Product(
            id = 0, // ID will be generated when adding to list
            name = name,
            price = price,
            description = description,
            imageResId = R.drawable.logo
        )

        // Add product to the list in ProductFragment
        val productFragment = getProductFragment()
        productFragment?.addProduct(newProduct)

        // Show success message
        Toast.makeText(requireContext(), "เพิ่มสินค้า '$name' เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show()

        // Clear form
        clearForm()
    }

    private fun getProductFragment(): ProductFragment? {
        // Find the ProductFragment from the parent FragmentManager
        return parentFragmentManager.fragments.find { it is ProductFragment } as? ProductFragment
    }

    private fun clearForm() {
        editProductName.text.clear()
        editProductPrice.text.clear()
        editProductDescription.text.clear()
        editProductName.requestFocus()
    }
}