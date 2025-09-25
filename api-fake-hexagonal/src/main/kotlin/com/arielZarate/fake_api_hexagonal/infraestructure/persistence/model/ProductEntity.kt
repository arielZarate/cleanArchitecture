package com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model

import jakarta.persistence.*


@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val title: String,
    val price: Double,
    @Column(length = 700)
    val description: String,
    val category: String,
    val image: String,
    @Embedded
    val rating: RatingEntity
) {

    //constructor sin arg
    constructor() : this(
        id = 0,
        title = "",
        price = 0.0,
        description = "",
        category = "",
        image = "",
        rating = RatingEntity()
    )

}


