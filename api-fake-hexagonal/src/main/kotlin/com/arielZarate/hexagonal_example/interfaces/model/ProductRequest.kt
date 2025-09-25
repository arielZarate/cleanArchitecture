package com.arielZarate.hexagonal_example.interfaces.model


data class ProductRequest(
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingRequest
) {
    data class RatingRequest(val rate: Double, val count: Int)
}