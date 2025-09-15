package com.vachara.project02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var products: List<Product> = emptyList(),
    private val onItemClick: (Product) -> Unit = {},
    private val onEditClick: (Product) -> Unit = {},
    private val onDeleteClick: (Product) -> Unit = {}
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val editButton: Button = itemView.findViewById(R.id.editButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.productImage.setImageResource(product.imageResId)
        holder.productName.text = product.name
        holder.productPrice.text = "ราคา: ${product.price} บาท"
        holder.productDescription.text = product.description

        // การคลิกที่ item
        holder.itemView.setOnClickListener {
            onItemClick(product)
        }

        // ปุ่มแก้ไข
        holder.editButton.setOnClickListener {
            onEditClick(product)
        }

        // ปุ่มลบ
        holder.deleteButton.setOnClickListener {
            onDeleteClick(product)
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    // ฟังก์ชันสำหรับลบสินค้า
    fun removeProduct(product: Product) {
        val newList = products.toMutableList()
        newList.remove(product)
        updateProducts(newList)
    }

    // ฟังก์ชันสำหรับอัพเดทสินค้า
    fun updateProduct(oldProduct: Product, newProduct: Product) {
        val newList = products.toMutableList()
        val index = newList.indexOf(oldProduct)
        if (index != -1) {
            newList[index] = newProduct
            updateProducts(newList)
        }
    }
}