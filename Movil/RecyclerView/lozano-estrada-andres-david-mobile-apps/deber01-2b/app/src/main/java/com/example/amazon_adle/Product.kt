package com.example.amazon_adle

class Product(
    val id: Int,
    var title: String?,
    var imageResourceId: Int,
    var reviews: Int,
    var stars: Int,
    var price: Double,
    var discount: Double,
    var category: String?,
    var shippingToEcuador: Boolean
) {

}