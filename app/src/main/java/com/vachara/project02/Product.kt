package com.vachara.project02

class Product (
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageResId: Int = R.drawable.ic_product_placeholder
){
    // เพิ่มฟังก์ชัน copy เพื่อให้สามารถคัดลอกออบเจ็กต์ได้
    fun copy(
        id: Int = this.id,
        name: String = this.name,
        price: Double = this.price,
        description: String = this.description,
        imageResId: Int = this.imageResId
    ): Product {
        return Product(id, name, price, description, imageResId)
    }
}