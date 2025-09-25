package com.arielZarate.fake_api_hexagonal.interfaces.mappers

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.interfaces.model.ProductRequest
import com.arielZarate.fake_api_hexagonal.interfaces.model.ProductResponse
import org.springframework.stereotype.Component


@Component
class ProductMapper {
    fun mapToDomain(productRequest: ProductRequest): Product {

        return Product(
            title = productRequest.title,
            price = productRequest.price,
            description = productRequest.description,
            category = productRequest.category,
            image = productRequest.image,
            rating = Product.Rating(
                rate = productRequest.rating.rate, count = productRequest.rating.count
            )
        )
    }


    fun mapToResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id ?: 0,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = ProductResponse.RatingResponse(
                rate = product.rating.rate, count = product.rating.count
            ),
        )
    }
}