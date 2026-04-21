package com.arielZarate.fake_api_hexagonal.infraestructure.rest

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.mapper.ProductMapper
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.model.ProductDto
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.provider.WebClientMethod
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.provider.WebClientProvider
import org.springframework.stereotype.Component
import java.net.URI

@Component
class ApiFakeStoreClient(
    private val webClientProvider: WebClientProvider,
    private val productMapper: ProductMapper
) {

    val urlBase = "https://fakestoreapi.com"

    fun getAllProducts(): List<Product> {
        val response: Array<ProductDto> = webClientProvider.applyWithoutBody(
            clientName = "FakeStoreAPI",
            method = WebClientMethod.GET,
            uri = URI("$urlBase/products"),
            timeout = 10000L,
            headers = null,
            responseTypeReference = Array<ProductDto>::class.java
        )
        return response.toList().map { productMapper.toDomain(it) }
    }

    fun getProductById(id: Int): Product {
        val response: ProductDto = webClientProvider.applyWithoutBody(
            clientName = "FakeStoreAPI",
            method = WebClientMethod.GET,
            uri = URI("$urlBase/products/$id"),
            timeout = 10000L,
            headers = null,
            responseTypeReference = ProductDto::class.java
        )
        return productMapper.toDomain(response)
    }
}