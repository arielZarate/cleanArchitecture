package com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model

import jakarta.persistence.*


@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var title: String,
    var price: Double,
    @Column(length = 700)
    var description: String,
    var category: String,
    var image: String,
    @Embedded
    var rating: RatingEntity
) {


}


