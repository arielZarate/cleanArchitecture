package com.arielZarate.fake_api_hexagonal.infraestructure.persistence.mapper

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model.ProductEntity
import org.springframework.stereotype.Component


@Component
class ProductEntityMapper(
    private val ratingMapper: RatingMapper
) {


    fun mapToDomain(productEntity: ProductEntity): Product {
        return Product(
            id = productEntity.id,
            title = productEntity.title,
            price = productEntity.price,
            description = productEntity.description,
            category = productEntity.category,
            image = productEntity.image,
            // mapper en rating
            rating = ratingMapper.toDomain(productEntity.rating)
        )
    }

    fun mapToEntity(product: Product): ProductEntity {
        return ProductEntity(
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = ratingMapper.toEntity(product.rating)

        )
    }
}