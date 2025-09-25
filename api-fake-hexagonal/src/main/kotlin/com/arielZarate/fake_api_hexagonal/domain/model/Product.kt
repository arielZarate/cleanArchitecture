package com.arielZarate.fake_api_hexagonal.domain.model


data class Product(
    val id: Int?=null,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
) {
    data class Rating(val rate: Double, val count: Int)
}