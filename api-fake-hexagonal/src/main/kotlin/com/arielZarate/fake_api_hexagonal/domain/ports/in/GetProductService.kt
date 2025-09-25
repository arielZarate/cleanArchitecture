package com.arielZarate.fake_api_hexagonal.domain.ports.`in`

import com.arielZarate.fake_api_hexagonal.domain.model.Product

interface GetProductService {
    fun getProductById(id: Int): Product?
    fun getAllProducts(): List<Product>
    fun createProduct(product: Product):Product
}


