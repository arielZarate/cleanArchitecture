package com.arielZarate.hexagonal_example.domain.ports.`in`

import com.arielZarate.hexagonal_example.domain.model.Product

interface GetProductService {
    fun getProductById(id: Int): Product?
    fun getAllProducts(): List<Product>
    fun createProduct(product: Product):Product
}


