package com.arielZarate.fake_api_hexagonal.infraestructure.rest.mapper

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.model.ProductDto
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.model.RatingDto
import org.springframework.stereotype.Component

@Component
class ProductRestMapper {

    fun toDomain(dto: ProductDto): Product {
        return Product(
            id = dto.id,
            title = dto.title.take(255),
            price = dto.price,
            description = dto.description.take(700),
            category = dto.category,
            image = dto.image,
            rating = Product.Rating(dto.rating.rate, dto.rating.count)
        )
    }

    fun toDto(product: Product): ProductDto {
        return ProductDto(
            id = product.id ?: 0,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = RatingDto(product.rating.rate, product.rating.count)
        )
    }
}