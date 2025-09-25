package com.arielZarate.hexagonal_example.infraestructure.persistence.mapper

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.infraestructure.persistence.model.RatingEntity
import org.springframework.stereotype.Component


@Component
class RatingEntityMapper {


    //convierte de Product.Rating a RatingEntity
    fun toEntity(rating:Product.Rating):RatingEntity{
        return RatingEntity(
            rate = rating.rate,
            count = rating.count
        )
    }


    //  // Convierte RatingEntity a Product.Rating (dominio)
    fun toDomain(ratingEntity: RatingEntity):Product.Rating{
       return Product.Rating(
           rate=ratingEntity.rate,
           count = ratingEntity.count
       )
    }

}