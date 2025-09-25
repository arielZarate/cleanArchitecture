package com.arielZarate.fake_api_hexagonal.domain.ports.out

import com.arielZarate.fake_api_hexagonal.domain.model.Product

interface ProductsPortOut {

    fun findProductById(id: Int): Product?

    fun getAllProducts():List<Product>

    fun saveProduct(product: Product): Product
}