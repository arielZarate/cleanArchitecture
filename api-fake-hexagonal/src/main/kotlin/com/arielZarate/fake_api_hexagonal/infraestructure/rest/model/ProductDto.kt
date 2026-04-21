package com.arielZarate.fake_api_hexagonal.infraestructure.rest.model

data class ProductDto (
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
)