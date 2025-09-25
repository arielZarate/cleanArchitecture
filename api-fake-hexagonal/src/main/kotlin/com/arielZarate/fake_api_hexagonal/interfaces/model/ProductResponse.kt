package com.arielZarate.fake_api_hexagonal.interfaces.model

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingResponse
) {
    data class RatingResponse(val rate: Double, val count: Int)
}