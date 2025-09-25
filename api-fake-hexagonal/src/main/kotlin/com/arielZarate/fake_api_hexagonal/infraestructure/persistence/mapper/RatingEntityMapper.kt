package com.arielZarate.fake_api_hexagonal.infraestructure.persistence.mapper

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model.RatingEntity
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